import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss']
})
export class InputComponent {
  @Input() form: FormGroup;
  @Input() name: string;
  @Input() value: string;
  @Input() label: string;
  @Input() placeholder: string;
  @Input() required: boolean;
  @Input() hidden: boolean;
  @Input() hint: string;
  @Input() errorMessage: string;
  @Input() disabled: boolean;
  @Input() inputType: string;
}

