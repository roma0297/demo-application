import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-credit-stock',
  templateUrl: './credit-stock.component.html',
  styleUrls: ['./credit-stock.component.scss']
})
export class CreditStockComponent implements OnInit {

  config = {
    fields: [
      {
        type: 'column',
        fields: [
            { type: 'header', value: 'Кредитная биржа' },
            {
              type: 'text',
              value:
                'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.'
            },
            { type: 'divider' },
            {
              type: 'table-api',
              label: 'Предложения',
              buttons: [
                { label: 'Refresh Data', type: 'refresh' },
                { label: 'Add Country', type: 'create' }
              ],
              columnIds: ['id', 'company', 'status', 'sum',  'duration', 'profit', '_read'],
              columnNames: ['id', 'Юр. лицо', 'Статус запроса', 'Сумма,руб.', 'Срок', 'Прибыль,%', ''],
              columnSizes: [50, 150, 150, 150, 150, 150, 46],
              api: '/api/countries?main=true',
              // data: ['asdfd', 'asdfds', 'asdf', 'asdf', 'asdfds', 'asdfdsa', '']
              data: [
                {
                id: 1,
                company: 'ИП Ярославкин',
                status: 'продажа',
                sum: '2млн.',
                duration: '3 мес.',
                profit: 10
              },
                {
                id: 2,
                company: 'ИП Окунев',
                status: 'покупка',
                sum: '700тыс.',
                duration: '3 мес.',
                profit: 6
              },
                {
                id: 3,
                company: 'ИП Северный',
                status: 'покупка',
                sum: '1.3млн.',
                duration: '3 мес.',
                profit: 5
              },
                {
                id: 4,
                company: 'ИП Южный',
                status: 'продажа',
                sum: '3,5млн.',
                duration: '8 мес.',
                profit: 6
              },
                {
                id: 5,
                company: 'ИП Западный',
                status: 'продажа',
                sum: '2млн. руб',
                duration: '3 мес.',
                profit: 10
              },
                {
                id: 6,
                company: 'ИП Восточный',
                status: 'покупка',
                sum: '15млн. руб',
                duration: '1 г.',
                profit: 12
              }
            ]
          }
        ],
        columnSize: '1'
      }
    ]
  }

  constructor() { }

  ngOnInit() {
  }

}
