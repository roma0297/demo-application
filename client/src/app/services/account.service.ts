import { Injectable } from '@angular/core';
import { ComponentPortal } from '@angular/cdk/portal';
import { Subject } from 'node_modules/rxjs';

import { TTab } from './../../common/types/market-place.d';
import { AnalyticsComponent } from '../components/analytics/analytics.component';
import { MarketListComponent } from '../components/market-list/market-list.component';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  tabsSubject: Subject<TTab[]> = new Subject<TTab[]>();
  private tabsList: TTab[] = [
    {
      id: 1,
      label: 'Аналитика',
      content: new ComponentPortal(AnalyticsComponent)
    },
    {
      id: 2,
      label: 'Счета и платежи',
    },
    {
      id: 3,
      label: 'Выписки и отчеты',
    },
    {
      id: 4,
      label: 'Контрагенты',
    },
    {
      id: 5,
      label: 'Шаблоны и автоплтежи',
    },
    {
      id: 6,
      label: 'Кредитная биржа',
      // content: new ComponentPortal(NavigationComponent)
    },
    {
      id: 7,
      label: 'Расширения',
      content: new ComponentPortal(MarketListComponent)
    }
  ];

  constructor() { }

  get tabs() {
    return this.tabsList;
  }

  set tabs(tabs: TTab[]) {
    this.tabsList = tabs;
  }

  manageTabList(tab: TTab, enabled: boolean) {
    if (enabled) {
      this.tabs = [...this.tabs, tab];
    } else {
      const index = this.tabs.findIndex((element: TTab) => element.id === tab.id);
      this.tabs = [...this.tabs.slice(0, index), ...this.tabs.slice(index + 1)];
    }
    this.tabsSubject.next(this.tabs);
  }
}
