import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { TObject } from 'src/common/types/market-place';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  config = {
    formClass: 'login_form',
    fields: [
      {
        type: 'column',
        fields: [
          {
            type: 'header',
            value: 'Авторизация',
            class: ['text-center'],
          },
          {
            type: 'text',
            class: ['color-gray', 'text-center'],
            value: 'Пожалуйста, авторизуйтесь или пройдите регистрацию для продолжения работы с банком.'
          },
          { type: 'divider' }
        ],
        columnSize: '1'
      },
      {
        type: 'column',
        fields: [
          { 
            type: 'input',
            label: 'Имя пользователя',
            errorMessage: 'Пожалуйста, введите корректное имя пользователя',
            fieldName: 'loginName',
            placeholder: 'Имя пользователя',
            value: '',
            required: true
          },
          { 
            type: 'input',
            label: 'Пароль',
            errorMessage: 'Пожалуйста, введите пароль',
            fieldName: 'password',
            placeholder: 'Пароль',
            value: '',
            required: true
          },
          { 
            type: 'button',
            text: 'Войти в аккаунт',
            class: 'login_btn--center',
            buttonType:'submit'
          },
          { 
            type: 'button',
            text: 'Зарегистрироваться',
            class: 'login_btn--center',
            buttonType: 'button'
          },
        ]
      }

    ]
  };

  constructor(private router: Router) { }

  onFormData(formData: TObject) {
    const data = formData.reduce((acc, { name, value }) => {
      acc[name] = value;
      return acc;
    }, {});

    if (data.loginName === 'admin' && data.password === '1234') {
      this.router.navigate(['/account']);
    }
  }

}
