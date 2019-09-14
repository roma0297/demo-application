import { Component, Input } from "@angular/core";
import { FormGroup } from "@angular/forms";

@Component({
  selector: 'app-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.scss']
})
export class CheckboxComponent {
  @Input() form: FormGroup;
  @Input() name: string;
  @Input() value: string;
  @Input() label: string;
  @Input() checked: boolean;
  @Input() disabled: boolean;
  @Input() hint: string;
}
