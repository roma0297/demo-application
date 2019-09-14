import { Component } from '@angular/core';
import { ComponentPortal } from '@angular/cdk/portal';

import { AnalyticsComponent } from './../analytics/analytics.component';
import { TTab } from './../../../common/types/market-place.d';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrls: ['./account-page.component.scss']
})
export class AccountPageComponent {
  // userSettingsPortal: any;

  tabs: TTab[] = [
    {
      label: 'Аналитика',
      content: new ComponentPortal(AnalyticsComponent)
    },
    {
      label: 'Счета и платежи',
      // content: new ComponentPortal(NavigationComponent)
    },
    {
      label: 'Выписки и отчеты',
      // content: new ComponentPortal(NavigationComponent)
    },
    {
      label: 'Контрагенты',
      // content: new ComponentPortal(NavigationComponent)
    },
    {
      label: 'Шаблоны и автоплтежи',
      // content: new ComponentPortal(NavigationComponent)
    },
    {
      label: 'Расширения',
      content: new ComponentPortal(NavigationComponent)
    },
    {
      label: 'Кредитная биржа',
      // content: new ComponentPortal(NavigationComponent)
    },
  ];

}
