
import {
  Component,
  Input,
  OnChanges,
  Output,
  EventEmitter,
  SimpleChanges,
  AfterContentInit,
  ChangeDetectorRef,
  OnDestroy
} from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Subject, Subscription } from 'node_modules/rxjs';
// import DepsCallbacksService from "./deps-callbacks";


export interface Button {
  label: string;
  link?: string;
  click?(): any;
}

export interface Fields {
  type: string;
  fieldName?: string;
  label?: string;
  value?: string;
  placeholder?: string;
  attributes?: any[];
  fields?: any[];
  columnSize?: string;
  required?: boolean;
  options?: any[];
  buttons?: Button[];
  clickable?: boolean;
  deps?: string[];
  onDepsChange?: any;

  headers?: [];
  data?: [];
}

export interface FormResultData {
  label: string; // For JIRA
  value: any;
  name: string;
}

export interface DynamicFormConfig {
  submitPath?: string | boolean; // Deprecated. Should create submit button via button component
  submitText?: string; // Deprecated. Should create submit button via button component
  submitSuccessRedirect?: string; // Deprecated. Should create submit button via button component
  fields: Fields[];
}

export interface Message {
  type: 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'light' | 'dark'; // according to bootstrap default notifications
  text: string;
}

type TCheckCaptchaResponse = {
  success: boolean,
  error?: Error,
  score?: number,
  action?: string,
  challenge_ts?: string,
  hostname?: string,
  'error-codes'?: any[]
}

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.scss'],
  providers: [
    FormBuilder,
    HttpClient,
    // DepsCallbacksService
  ]
})
export class DynamicFormComponent implements OnChanges, AfterContentInit, OnDestroy {
  @Input() config: DynamicFormConfig;
  @Input() message: Message;
  @Input() errorSubject$: Subject<any>;
  @Input() isCaptchaEnabled: boolean;
  @Output() onChanged = new EventEmitter<FormResultData[]>(); // Deprecated. Should use onFormEvent instead.
  @Output() onFormEvent = new EventEmitter<object>();

  saving: Boolean = false;
  error: string = '';
  formName: string;
  form: FormGroup;
  fields: Fields[];
  canSubmit: boolean = false;
  pathToSaveForm: string;
  submitText: string;
  step: number;
  showClose: boolean;
  formData: FormResultData[];
  previousStepPath: string;
  private submitSuccessRedirect: string;
  private flatFieldList = [];
  private dependencyList: Set<string>;
  method: 'post' | 'put' = 'post';
  subscriptions: Array<Subscription> = [];
  private formFields;
  readOnly;

  constructor(
    private fb: FormBuilder,
    private cdr: ChangeDetectorRef,
    // private depsCallbackService: DepsCallbacksService,
    private httpClient: HttpClient
  ) { }

  getColumnClass(columnSize) {
    switch (columnSize) {
      case '1/5':
        return 'col-20';
      case '1/4':
        return 'col-25';
      case '1/3':
        return 'col-33';
      case '1/2':
        return 'col-50';
      default:
        return 'col-100';
    }
  }

  ngOnInit() {
    this.runFormBuilder();
    this.spyOnErrors();
  }

  ngOnChanges(simpleChanges: SimpleChanges) {
    if (simpleChanges.config && simpleChanges.config.previousValue) {
      if (JSON.stringify(simpleChanges.config.currentValue) !== JSON.stringify(simpleChanges.config.previousValue)) { // For async loaded form
        this.runFormBuilder();
        return;
      }

      const fields = this.createConfigForReactiveForm(this.flatFieldList);
      this.form = this.fb.group(fields);
      this.cdr.detectChanges();
    }
  }

  ngAfterContentInit() {
    this.cdr.detectChanges();
  }

  spyOnErrors() {
    if (this.errorSubject$) {
      const subscription = this.errorSubject$
        .subscribe((res) => {
          res.controls.forEach(name => {
            this.form.get(name).setErrors({ alreadyExist: true });
          });
          this.message = { type: 'danger', text: res.errorMessage };
        });
      this.subscriptions.push(subscription);
    }
  }

  runFormBuilder() {
    const config = this.config || JSON.parse(document.body.dataset.formConfig);
    this.fields = config.fields;
    this.formName = config.name || Symbol('form-name');
    this.step = config.step;
    this.showClose = config.showClose;
    this.previousStepPath = config.previousStepPath;
    this.pathToSaveForm = config.submitPath;
    this.submitText = config.submitText;
    this.submitSuccessRedirect = config.submitSuccessRedirect;
    this.readOnly = config.readOnly || false;
    this.canSubmit = !!this.pathToSaveForm || !!this.submitSuccessRedirect;
    this.flatFieldList = this.getFieldsFromConfig(this.fields);
    this.formFields = this.createConfigForReactiveForm(this.flatFieldList);

    this.form = this.fb.group(this.formFields);
    // this.getFieldDeps();
    // this.depsEvents();
  }

  // getFieldDeps() {
  //   this.dependencyList = new Set();

  //   this.fields.forEach(column => {
  //     column.fields.forEach(field => {
  //       if (field.deps) {
  //         field.deps.forEach(dep => this.dependencyList.add(dep));
  //       }
  //     });
  //   });
  // }

  // depsEvents() {
  //   this.dependencyList.forEach((element) => {
  //     this.form.get(element).valueChanges.subscribe(data => {
  //       this.dependentFields(element, data);
  //     });
  //   });
  // }

  // dependentFields(element, data) {
  //   this.fields.forEach(column => {
  //     column.fields.forEach(currentField => {
  //       if (currentField.depsCallbackName && currentField.deps.some(dep => dep === element)) {
  //         const changedField = {};
  //         changedField[element] = data;
  //         this.depsCallbackService.callbacks[currentField.depsCallbackName](changedField, currentField, this.form, this.formName);
  //         this.onFormEvent.emit({
  //           type: 'deps',
  //           form: this.form,
  //           changedField,
  //           element: currentField,
  //           formData: this.submit(true)
  //         });
  //       }
  //     });
  //   });
  // }

  formEvents(type, event, element): void {
    if (type === 'counter' && !element.clickable) {
      return;
    }

    this.onFormEvent.emit({
      type,
      event,
      button: element,
      form: this.form,
      formData: this.submit(true)
    });
  }

  onSubmit(preventOnChangedEvent?) {
    if (this.isCaptchaEnabled) {
      this.checkCaptcha(preventOnChangedEvent);
      return;
    }
    this.submitForm(preventOnChangedEvent);
  }

  checkCaptcha(preventOnChangedEvent) {
    const token = this.form.get('recaptchaReactive').value;
    this.httpClient
      .get(`/api/check-captcha?token=${token}`)
      .subscribe(
        (response: TCheckCaptchaResponse) => {
          if (response.success) {
            this.submitForm(preventOnChangedEvent);
          }
        },
        error => console.log(`Error during captcha check: ${error.message}`));
  }

  submitForm(preventOnChangedEvent) {
    this.markAllAsTouched(this.form);
    this.submit(preventOnChangedEvent);
  }

  submit(preventOnChangedEvent?) {
    if (this.form.valid) {
      this.formData = this.flatFieldList.map(element => ({
        label: element.label,
        value: this.form.value[element.fieldName],
        name: element.fieldName
      }));

      if (!preventOnChangedEvent) this.onChanged.emit(this.formData);
      return this.formData;
    }

    return [];
  }

  createConfigForReactiveForm(flatFieldList) {
    const fields = {};

    flatFieldList.forEach((field: any) => {
      if (field.type === 'group') {

        const fromControls = field.groups.from
          .map(element => this.fb.group({
            name: element.name,
            translation: element.translation
          }));

        const toControls = field.groups.to
          .map(element => this.fb.group({
            name: element.name,
            translation: element.translation
          }));

        const value = this.fb.group({
          from: this.fb.array(fromControls),
          to: this.fb.array(toControls, [Validators.required])
        });

        fields[field.fieldName] = value;
        return;
      } else if (field.type === 'translation') {
        fields[field.fieldName] = this.fb.array(
          field.fields.map(element => this.fb.group({
            name: element.name,
            subject: element.subject,
            message: element.message
          }))
        );

        return;
      }

      const validators = [];
      // if (field.required) {
      //   validators.push(Validators.required);
      // }
      // if (field.isNumber) {
      //   validators.push(ValidationService.isNumber);
      // }
      // if (field.isEmail) {
      //   validators.push(ValidationService.isEmail);
      // }
      // if (field.fieldName === 'emailConfirmation') {
      //   validators.push(ValidationService.areEmailsEqual);
      // }

      fields[field.fieldName] = [field.value || '', validators];
    });

    return this.isCaptchaEnabled ?
      Object.assign({}, fields, { recaptchaReactive: new FormControl(null, Validators.required) }) : fields;
  }

  private flatList = [];
  private getFieldsFromConfig(treeData) {
    treeData.forEach(field => {
      if (field.type === 'column') {
        this.getFieldsFromConfig(field.fields);
      } else {
        if (field.type !== 'header' && field.type !== 'text' && field.type !== 'divider' && field.type !== 'button') {
          this.flatList.push(field);
        }
      }
    });

    return this.flatList;
  }

  private markAllAsTouched(form: FormGroup) {
    Object.keys(form.controls).map((controlName) => {
      form.get(controlName).markAsTouched({ onlySelf: true });
    });
  }

  ngOnDestroy() {
    this.subscriptions
      .forEach((subscription: Subscription) => subscription.unsubscribe());
  }
}
