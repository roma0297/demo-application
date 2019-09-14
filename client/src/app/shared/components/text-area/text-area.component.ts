import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-textarea',
  templateUrl: './text-area.component.html',
  styleUrls: ['./text-area.component.scss']
})
export class TextAreaComponent {
  @Input() form: FormGroup;
  @Input() name: string;
  @Input() value: string;
  @Input() label: string;
  @Input() placeholder: string;
  @Input() required: boolean;
  @Input() hint: string;
  @Input() errorMessage: string;
  @Input() minHeight: string;
  @Input() maxHeight: string;
}

