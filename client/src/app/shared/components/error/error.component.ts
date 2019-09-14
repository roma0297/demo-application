import { Component, Input } from "@angular/core";
import { FormControl } from "@angular/forms";

@Component({
  selector: "error-message",
  template: `
    <span *ngIf="errorMessage !== null">{{ text }}</span>
  `
})
export class ErrorMessageComponent {
  @Input() control: FormControl;
  @Input() message: string;
  text: string;

  get errorMessage() {
    for (const propertyName in this.control.errors) {
      if (this.control.errors.hasOwnProperty(propertyName) && this.control.touched) {
        this.text = this.message;
        return this.text;
      }
    }

    return null;
  }
}
