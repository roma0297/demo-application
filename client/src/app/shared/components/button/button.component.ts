import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent {
  @Input() form: FormGroup;
  @Input() id: string | number;
  @Input() label: string;
  @Input() text: string;
  @Input() params: object;
  @Input() style: string;
  @Input() class: string;
  @Input() type: string;
}

