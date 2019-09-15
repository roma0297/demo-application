import { MarketItem } from './../components/market-item/market-item.model';
import { Injectable, Injector } from '@angular/core';
import { ComponentPortal, PortalInjector } from '@angular/cdk/portal';
import { Subject } from 'rxjs';

import { TTab, IMarketItem } from './../../common/types/market-place.d';
import { AnalyticsComponent } from '../components/analytics/analytics.component';
import { CreditStockComponent } from '../components/credit-stock/credit-stock.component';
import { ExtensionWrapperComponent } from '../components/extension-wrapper/extension-wrapper.component';
import { CONTAINER_DATA } from '../configs/tokens';
import { getTabConfig } from '../configs/tab-configs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  marketItemsSubject: Subject<IMarketItem[]> = new Subject<IMarketItem[]>();
  private marketItemsList: IMarketItem[] =   [
    new MarketItem({
      id: 1,
      itemName: 'target',
      name: 'Таргетированная реклама',
      subtitle: 'Сервис по продвижению бизнеса в интернете',
      url: '/assets/img/target.jpg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    }),
    new MarketItem({
      id: 2,
      itemName: 'calls',
      name: 'Холодные звонки',
      subtitle: 'Сервис по продвижению бизнеса по телефону',
      url: '/assets/img/calls.jpg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    }),
    new MarketItem({
      id: 3,
      itemName: 'staffing',
      name: 'Подбор персонала',
      subtitle: 'Сервис по подбору персонала на открытые вакансии',
      url: '/assets/img/staffing.jpeg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    }),
    new MarketItem({
      id: 4,
      itemName: 'support',
      name: 'Поддержка сайта',
      subtitle: 'Сервис по поддержке сайта',
      url: '/assets/img/tech-support.png',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    }),
    new MarketItem({
      id: 5,
      itemName: 'audit',
      name: 'Выездной аудит',
      subtitle: 'Сервис по проведению внешнего аудита',
      url: '/assets/img/audit.jpg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    })
  ];

  tabsSubject: Subject<TTab[]> = new Subject<TTab[]>();
  private tabsList: TTab[] = [];

  constructor(private injector: Injector) { }

  get tabs() {
    return this.tabsList;
  }

  set tabs(tabs: TTab[]) {
    this.tabsList = tabs;
    this.tabsSubject.next(this.tabsList);
  }

  get marketItems() {
    return this.marketItemsList;
  }

  set marketItems(marketItems: IMarketItem[]) {
    this.marketItemsList = marketItems;
    this.marketItemsSubject.next(this.marketItemsList);
  }

  setTablistConfig(component) {
    this.tabs = [
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
        content: new ComponentPortal(component)
      }
    ];
    this.tabsSubject.next(this.tabs);
  }

  manageTabList(marketItem: IMarketItem, enabled: boolean) {
    console.log('this is arguments', arguments);
    const { id, name: label, itemName: tabName } = marketItem;
    const newTab = {
      id,
      label,
      tabName,
      content: new ComponentPortal(ExtensionWrapperComponent, null, this.createInjector(getTabConfig(tabName)))
    };

    if (enabled) {
      this.tabs = [...this.tabs, newTab];
    } else {
      const index = this.tabs.findIndex((element: TTab) => element.id === marketItem.id);
      this.tabs = [...this.tabs.slice(0, index), ...this.tabs.slice(index + 1)];
    }
    this.tabsSubject.next(this.tabs);
  }

  manageMarketItems(item: IMarketItem, enabled: boolean) {
    if (enabled) {
      const index = this.marketItems.findIndex((marketItem: IMarketItem) => marketItem.id === item.id);
      const updatedItem = { ...item, enabled: true, isShown: false };
      this.marketItems = [...this.marketItems.slice(0, index), updatedItem, ...this.marketItems.slice(index + 1)];
      // removing element from panel after animation
      setTimeout(() => {
        this.marketItems = [...this.marketItems.slice(0, index), ...this.marketItems.slice(index + 1)];
      }, 1300);
      this.manageTabList(item, enabled);
    } else if (!enabled) {
      this.marketItems = [...this.marketItems, item];
    }
  }

  createInjector(dataToPass: any): PortalInjector {
    const injectorTokens = new WeakMap();
    injectorTokens.set(CONTAINER_DATA, dataToPass);
    return new PortalInjector(this.injector, injectorTokens);
  }
}
