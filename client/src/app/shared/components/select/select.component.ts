import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

export interface Option {
  name: string;
  value: string;
  selected?: boolean;
}

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent implements OnInit {
  @Input() form: FormGroup;
  @Input() name: string;
  @Input() value: string;
  @Input() label: string;
  @Input() placeholder: string;
  @Input() required: boolean;
  @Input() options: Option[];
  @Input() haveEmptyOption: boolean;
  @Input() hidden: boolean;
  @Input() hint: string;
  @Input() errorMessage: string;
  @Input() disabled: boolean;
  @Input() private api: string;

  selectedItem: string;

  constructor(private httpClient: HttpClient) { }

  setValue(value) {
    this.form.get(this.name).setValue(value);
  }

  ngOnInit() {

    if (this.haveEmptyOption === undefined) {
      this.haveEmptyOption = true;
    }

    if (this.api) {
      this.getData(this.api).subscribe(options => {
        this.options = [...(this.options || []), ...(options || [])];
        this.setDefault(this.options);
      });
    } else {
      this.setDefault(this.options);
    }
  }

  getData(api): any {
    return this.httpClient.get<any[]>(api);
  }

  setDefault(options: Option[]) {
    this.selectedItem = options.reduce((result, option) =>
      option.selected ? option.value : result,
      options.length && !this.haveEmptyOption ? options[0].value : ''
    );
    this.form.controls[this.name].setValue(this.selectedItem, { onlySelf: true });
  }
}

