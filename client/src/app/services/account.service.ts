import { MarketItem } from './../components/market-item/market-item.model';
import { Injectable, InjectionToken, Injector } from '@angular/core';
import { ComponentPortal, PortalInjector } from '@angular/cdk/portal';
import { Subject } from 'rxjs';

import { TTab } from './../../common/types/market-place.d';
import { AnalyticsComponent } from '../components/analytics/analytics.component';
import { MarketListComponent } from '../components/market-list/market-list.component';
import { CreditStockComponent } from '../components/credit-stock/credit-stock.component';

export const CONTAINER_DATA = new InjectionToken<{}>('CONTAINER_DATA');
@Injectable({
  providedIn: 'root'
})
export class AccountService {

  tabsSubject: Subject<TTab[]> = new Subject<TTab[]>();
  private tabsList: TTab[] = [
    {
      id: 20000000,
      label: 'Аналитика',
      content: new ComponentPortal(AnalyticsComponent)
    },
    {
      id: 20000001,
      label: 'Счета и платежи',
    },
    {
      id: 20000002,
      label: 'Выписки и отчеты',
    },
    {
      id: 20000003,
      label: 'Контрагенты',
    },
    {
      id: 20000004,
      label: 'Шаблоны и автоплтежи',
    },
    {
      id: 20000005,
      label: 'Кредитная биржа',
      content: new ComponentPortal(CreditStockComponent)
    },
    {
      id: 20000006,
      label: 'Расширения',
      content: new ComponentPortal(MarketListComponent)
    }
  ];

  constructor(private injector: Injector) { }

  get tabs() {
    return this.tabsList;
  }

  set tabs(tabs: TTab[]) {
    this.tabsList = tabs;
  }

  createInjector(dataToPass): PortalInjector {
    const injectorTokens = new WeakMap();
    injectorTokens.set(CONTAINER_DATA, dataToPass);
    return new PortalInjector(this.injector, injectorTokens);
}
}
