import { HttpClient } from '@angular/common/http';
import { Injectable, Injector, Inject } from '@angular/core';
import { ComponentPortal, PortalInjector } from '@angular/cdk/portal';
import { Subject } from 'rxjs';

import { TTab, IMarketItem, TObject } from './../../common/types/market-place.d';
import { AnalyticsComponent } from '../components/analytics/analytics.component';
import { CreditStockComponent } from '../components/credit-stock/credit-stock.component';
import { ExtensionWrapperComponent } from '../components/extension-wrapper/extension-wrapper.component';
import { CONTAINER_DATA, BASE_URL } from '../configs/tokens';
import { getTabConfig } from '../configs/tab-configs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  // should be refactored
  error: TObject = {};
  marketItemsSubject: Subject<IMarketItem[]> = new Subject<IMarketItem[]>();
  private marketItemsList: IMarketItem[] = [];

  tabsSubject: Subject<TTab[]> = new Subject<TTab[]>();
  private tabsList: TTab[] = [];

  constructor(
    private injector: Injector,
    @Inject(BASE_URL) private baseUrl: string,
    private http: HttpClient) {
      this.getMarketItems();
      this.getUsersPlugins();
    }

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

  addMarketItem(body: IMarketItem) {
    return this.http
      .post(`${this.baseUrl}/api/account/plugins/add/${body.id}`, {})
      .subscribe(() => {
        const index = this.marketItems.findIndex((marketItem: IMarketItem) => marketItem.id === body.id);
        const updatedItem = { ...body, enabled: true, isShown: false };
        this.marketItems = [...this.marketItems.slice(0, index), updatedItem, ...this.marketItems.slice(index + 1)];
        // removing element from panel after animation
        setTimeout(() => {
          this.marketItems = [...this.marketItems.slice(0, index), ...this.marketItems.slice(index + 1)];
        }, 1300);
        const { id, name: label, itemName: tabName } = body;
        const newTab = {
          id,
          label,
          tabName,
          content: new ComponentPortal(ExtensionWrapperComponent, null, this.createInjector(getTabConfig(tabName)))
        };
        this.tabs = [...this.tabs, newTab];
      },
      (error: Error) => this.error = error
    );
  }

  getMarketItems() {
    return this.http
      .get(`${this.baseUrl}/api/account/marketplace`)
      .subscribe((marketItems: any) => {
        this.marketItems = (<IMarketItem[]>marketItems.content);
      },
      (error: Error) => this.error = error
    );
  }

  getUsersPlugins() {
    return this.http
      .get(`${this.baseUrl}/api/account/plugins`)
      .subscribe((response: TObject) => {
        const { plugins } = response;
        const newTabs = plugins.map(({ id, name: label, itemName: tabName }) => ({
          id,
          label,
          tabName,
          content: new ComponentPortal(ExtensionWrapperComponent, null, this.createInjector(getTabConfig(tabName)))
        }));
        this.tabs = [...this.tabsList, ...newTabs];
      },
      (error: Error) => this.error = error
    );
  }

  removeUserPlugin(body) {
    return this.http
      .post(`${this.baseUrl}/api/account/plugins/remove/${body.id}`, {})
      .subscribe(() => {
        this.getMarketItems();
      });
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
        tabName: 'extentions',
        content: new ComponentPortal(component)
      }
    ];
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
