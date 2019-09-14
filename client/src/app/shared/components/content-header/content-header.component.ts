import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

interface Button {
  type: 'button' | 'link';
  text: string;
  id: string;
  click?: any;
  style?: string;
}

@Component({
  selector: 'app-content-header',
  templateUrl: './content-header.component.html',
  styleUrls: ['./content-header.component.scss']
})
export class ContentHeaderComponent {
  @Input() public form: FormGroup;
  @Input() public text: string;
  @Input() public buttons: Button[];
}
