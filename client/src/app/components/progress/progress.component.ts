import { Component, OnInit, HostListener } from '@angular/core';
import { TStep } from 'src/common/types/market-place';

@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.scss']
})
export class ProgressComponent implements OnInit {

  windowWidth: number;
  lineWidth: number;
  status = 'История обработки запроса на открытие ИП и регистрацию счета';
  steps: TStep[] = [
    {
      id: 1,
      title: 'Ваш запрос принят',
      finished: true,
      active: false,
      tooltipText: 'Here is the test text... you can place here anything you want',
      description: 'Уважаемый {No name}, благодарим вас за выбор нашего сервиса по оформлению расчетного счета. Ваша заявка принята в обработку и в ближашее время с вами свяжется наш специлист для сбора документов.'
    },
    {
      id: 2,
      title: 'Сбор документов',
      finished: true,
      active: true,
      tooltipText: 'Here is the test text... you can place here anything you want',
      description: 'Уважаемый {No Name}, документы по вашему обращению собраны и в ближайшее время будут переданы в обработку. Спасибо за обращение в наш банк.'
    },
    {
      id: 3,
      title: 'Обработка документов',
      finished: false,
      active: false,
      tooltipText: 'Here is the test text... you can place here anything you want',
      description: 'Уважаемый {No name}, в данный момент мы обрабатываем ваши документы. Если у вас есть какие-то дополнительные вопросы, вы можете задать их нам по телефону горячей линии. Спасибо.'
    },
    {
      id: 4,
      title: 'Открытие счета',
      finished: false,
      active: false,
      tooltipText: 'Here is the test text... you can place here anything you want',
      description: 'Уважаемый {No name}, в данный момент мы работаем над открытием расчетного счета для вашего бизнеса. Спасибо.'
    },
    {
      id: 5,
      title: 'Ваш запрос завершен',
      finished: false,
      active: false,
      tooltipText: 'Here is the test text... you can place here anything you want',
      description: 'Уважаемый {No name}, расчетный счет успешно открыт, спасибо за обращение в наш банк. Для получения деталей вы можете пройти в личный кабинет или обратиться по телефону горячей линии. Спасибо.'
    }
  ];

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.getBulletLineWidth();
  }

  constructor() {}

  ngOnInit() {
    this.getBulletLineWidth();
  }

  getBulletLineWidth() {
    this.windowWidth = window.innerWidth;
    this.lineWidth = this.windowWidth / this.steps.length - 50;
  }

}
