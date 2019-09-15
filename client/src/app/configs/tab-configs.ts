import { TObject } from '../../common/types/market-place';

export function getTabConfig(tabName: string) {
  switch (tabName) {
    case 'target':
      return targetConfig;
    case 'calls':
      return callsConfig;
    case 'audit':
      return auditConfig;
    case 'support':
      return supportConfig;
    case 'staffing':
      return staffingConfig;
    default:
      return {
        fields: []
      };
  }
}

const staffingConfig = {
  fields: [
    {
      type: 'column',
      fields: [
        {
          type: 'header',
          value: 'Сервис по привлечению клиентов - "Холодные звонки"',
          class: ['text-center'],
        },
        {
          type: 'text',
          class: ['color-gray', 'text-center'],
          value: 'Чтобы активировать услугу нам необходимо, чтобы вы предоставили нам некоторые данные:'
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
          label: 'Род деятельности',
          errorMessage: 'Укажите, пожалуйста, род деятельности',
          fieldName: 'businessType',
          placeholder: 'Название деятельности',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'Что планируете продавать?',
          errorMessage: 'Пожалуйста, укажите род продукции',
          fieldName: 'products',
          placeholder: 'Род продукции',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'В каком регионе вы планируете развиваться?',
          errorMessage: 'Пожалуйста, укажите регион',
          fieldName: 'region',
          placeholder: 'Ленинградская область',
          value: '',
          required: true,
          inputType: 'password'
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_1',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_2',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_3',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'button',
          text: 'Войти в аккаунт',
          class: 'login_btn--center',
          buttonType: 'submit'
        }
      ]
    }
  ]
};

const supportConfig = {
  fields: [
    {
      type: 'column',
      fields: [
        {
          type: 'header',
          value: 'Сервис по привлечению клиентов - "Холодные звонки"',
          class: ['text-center'],
        },
        {
          type: 'text',
          class: ['color-gray', 'text-center'],
          value: 'Чтобы активировать услугу нам необходимо, чтобы вы предоставили нам некоторые данные:'
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
          label: 'Род деятельности',
          errorMessage: 'Укажите, пожалуйста, род деятельности',
          fieldName: 'businessType',
          placeholder: 'Название деятельности',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'Что планируете продавать?',
          errorMessage: 'Пожалуйста, укажите род продукции',
          fieldName: 'products',
          placeholder: 'Род продукции',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'В каком регионе вы планируете развиваться?',
          errorMessage: 'Пожалуйста, укажите регион',
          fieldName: 'region',
          placeholder: 'Ленинградская область',
          value: '',
          required: true,
          inputType: 'password'
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_1',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_2',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_3',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'button',
          text: 'Войти в аккаунт',
          class: 'login_btn--center',
          buttonType: 'submit'
        }
      ]
    }
  ]
};

const auditConfig = {
  fields: [
    {
      type: 'column',
      fields: [
        {
          type: 'header',
          value: 'Сервис по привлечению клиентов - "Холодные звонки"',
          class: ['text-center'],
        },
        {
          type: 'text',
          class: ['color-gray', 'text-center'],
          value: 'Чтобы активировать услугу нам необходимо, чтобы вы предоставили нам некоторые данные:'
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
          label: 'Род деятельности',
          errorMessage: 'Укажите, пожалуйста, род деятельности',
          fieldName: 'businessType',
          placeholder: 'Название деятельности',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'Что планируете продавать?',
          errorMessage: 'Пожалуйста, укажите род продукции',
          fieldName: 'products',
          placeholder: 'Род продукции',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'В каком регионе вы планируете развиваться?',
          errorMessage: 'Пожалуйста, укажите регион',
          fieldName: 'region',
          placeholder: 'Ленинградская область',
          value: '',
          required: true,
          inputType: 'password'
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_1',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_2',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_3',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'button',
          text: 'Войти в аккаунт',
          class: 'login_btn--center',
          buttonType: 'submit'
        }
      ]
    }
  ]
};
const callsConfig = {
  fields: [
    {
      type: 'column',
      fields: [
        {
          type: 'header',
          value: 'Сервис по привлечению клиентов - "Холодные звонки"',
          class: ['text-center'],
        },
        {
          type: 'text',
          class: ['color-gray', 'text-center'],
          value: 'Чтобы активировать услугу нам необходимо, чтобы вы предоставили нам некоторые данные:'
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
          label: 'Род деятельности',
          errorMessage: 'Укажите, пожалуйста, род деятельности',
          fieldName: 'businessType',
          placeholder: 'Название деятельности',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'Что планируете продавать?',
          errorMessage: 'Пожалуйста, укажите род продукции',
          fieldName: 'products',
          placeholder: 'Род продукции',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'В каком регионе вы планируете развиваться?',
          errorMessage: 'Пожалуйста, укажите регион',
          fieldName: 'region',
          placeholder: 'Ленинградская область',
          value: '',
          required: true,
          inputType: 'password'
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_1',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_2',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'input',
          label: 'Custom field',
          errorMessage: 'Custom field',
          fieldName: 'custom_3',
          placeholder: 'Custom field',
          value: '',
          required: true,
        },
        {
          type: 'button',
          text: 'Войти в аккаунт',
          class: 'login_btn--center',
          buttonType: 'submit'
        }
      ]
    }
  ]
};

const targetConfig = {
  fields: [
    {
      type: 'column',
      fields: [
        {
          type: 'header',
          value: 'Таргетированная реклама',
          class: ['text-center'],
        },
        {
          type: 'text',
          class: ['color-gray', 'text-center'],
          value: 'Для завершения оформления услуги, пожалуйста введите слующие данные:'
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
          label: 'Род деятельности',
          errorMessage: 'Укажите, пожалуйста, род деятельности',
          fieldName: 'businessType',
          placeholder: 'Название деятельности',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'Куда вы хотите переводить пользователя?',
          errorMessage: 'Укажите ресурс, на который будут привлекаться пользователи',
          fieldName: 'host',
          placeholder: 'https://example.com',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'Укажите регион в котром вы хотите продвигаться',
          errorMessage: 'Пожалуйста, укажите регион',
          fieldName: 'region',
          placeholder: 'Москва и Московская область',
          value: '',
          required: true
        },
        {
          type: 'input',
          label: 'Какой планируете бюджет?',
          errorMessage: 'Пожалуйст, укажите примерный бюджет',
          fieldName: 'budget',
          placeholder: '100',
          value: '',
          required: true
        },
        {
          type: 'select',
          label: 'Выберите валюту в которой делать',
          fieldName: 'currency',
          options: [{ name: 'Rub', value: 'rub', selected: true}],
          value: '',
          required: false
        },
        {
          type: 'textarea',
          label: 'Если у вас остались еще какие-то комментарии, пожалуйста, укажите из тут:',
          fieldName: 'comments',
          placeholder: 'Комментарии',
          value: '',
          required: true
        },
        {
          type: 'button',
          text: 'Отправить',
          class: 'login_btn--center',
          buttonType: 'submit'
        }
      ]
    }
  ]
};