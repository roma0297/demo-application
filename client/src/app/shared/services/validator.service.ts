import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {
  getValidatorErrorMessage(validatorName: string, validatorValue?: any) {
    const config = {
      required: 'Required',
      invalidEmailAddress: 'Invalid email address',
      minlength: `Minimum length ${validatorValue.requiredLength}`,
      invalidNumberValue: 'Invalid number value'
    };

    return config[validatorName];
  }

  isEmail(control) {
    // RFC 2822 compliant regex
    if (
      control.value.toLowerCase().match(
        /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/
      )
    ) {
      return null;
    } else {
      return { invalidEmailAddress: true };
    }
  }

  isNumber(control) {
    if (typeof control.value === 'number') {
      return null;
    } else {
      return { invalidNumberValue: true };
    }
  }

  areEmailsEqual(control) {
    const emailControl = (control.parent) ? control.parent.controls.email : undefined;
    if (!emailControl || emailControl.value === control.value) {
      return null;
    } else {
      return { emailsAreNotEqual: true };
    }
  }
}

