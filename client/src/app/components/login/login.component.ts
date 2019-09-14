import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  config = {
    formClass: 'login_form',
    fields: [
      {
        type: 'column',
        fields: [
          { type: 'header', value: 'Авторизация' },
          {
            type: 'text',
            class: 'color-yellow',
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
            fieldName: 'loginName',
            placeholder: 'Пароль',
            value: '',
            required: true
          },
          { 
            type: 'button',
            label: 'Пароль',
            errorMessage: 'Пожалуйста, введите пароль',
            fieldName: 'loginName',
            placeholder: 'Пароль',
            value: '',
            required: true
          },
        ]
      }

    ]
  };

  constructor() { }

  ngOnInit() {
  }

}
