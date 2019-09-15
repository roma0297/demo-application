import { HttpClient } from '@angular/common/http';
import { Injectable, Inject } from '@angular/core';
import { Subject } from 'rxjs';

import { BASE_URL } from './../configs/tokens';
import { IMarketItem } from '../../common/types/market-place';
import { MarketItem } from '../components/market-item/market-item.model';

@Injectable({
  providedIn: 'root'
})
export class MarketItemService {

  marketItemsSubject: Subject<IMarketItem[]> = new Subject<IMarketItem[]>();
  private marketItemsList: IMarketItem[] =   [
    new MarketItem({
      id: 1,
      name: 'Таргетированная реклама',
      subtitle: 'Сервис по продвижению бизнеса в интернете',
      url: '/assets/img/target.jpg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    }),
    new MarketItem({
      id: 2,
      name: 'Холодные звонки',
      subtitle: 'Сервис по продвижению бизнеса по телефону',
      url: '/assets/img/calls.jpg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    }),
    new MarketItem({
      id: 3,
      name: 'Подбор персонала',
      subtitle: 'Сервис по подбору персонала на открытые вакансии',
      url: '/assets/img/staffing.jpeg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    }),
    new MarketItem({
      id: 4,
      name: 'Поддержка сайта',
      subtitle: 'Сервис по поддержке сайта',
      url: '/assets/img/tech-support.png',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    }),
    new MarketItem({
      id: 5,
      name: 'Выездной аудит',
      subtitle: 'Сервис по проведению внешнего аудита',
      url: '/assets/img/audit.jpg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      enabled: false,
      isShown: true
    })
  ];;

  constructor(@Inject(BASE_URL) private baseUrl: string, private http: HttpClient) {
    this.getMarketPlaceItems();
  }

  // [
  //   new MarketItem({
  //     id: 1,
  //     name: 'Таргетированная реклама',
  //     subtitle: 'Сервис по продвижению бизнеса в интернете',
  //     url: '/assets/img/target.jpg',
  //     description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
  //     enabled: false,
  //     isShown: true
  //   }),
  //   new MarketItem({
  //     id: 2,
  //     name: 'Холодные звонки',
  //     subtitle: 'Сервис по продвижению бизнеса по телефону',
  //     url: '/assets/img/calls.jpg',
  //     description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
  //     enabled: false,
  //     isShown: true
  //   }),
  //   new MarketItem({
  //     id: 3,
  //     name: 'Подбор персонала',
  //     subtitle: 'Сервис по подбору персонала на открытые вакансии',
  //     url: '/assets/img/staffing.jpeg',
  //     description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
  //     enabled: false,
  //     isShown: true
  //   }),
  //   new MarketItem({
  //     id: 4,
  //     name: 'Поддержка сайта',
  //     subtitle: 'Сервис по поддержке сайта',
  //     url: '/assets/img/tech-support.png',
  //     description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
  //     enabled: false,
  //     isShown: true
  //   }),
  //   new MarketItem({
  //     id: 5,
  //     name: 'Выездной аудит',
  //     subtitle: 'Сервис по проведению внешнего аудита',
  //     url: '/assets/img/audit.jpg',
  //     description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
  //     enabled: false,
  //     isShown: true
  //   })
  // ];

  getMarketPlaceItems() {
    return this.http
      .get(`${this.baseUrl}/account/marketplace`)
      .subscribe((items: IMarketItem[]) => {
        // this.marketItems = items;
        // this.marketItemsSubject.next(this.marketItems);
      });
  }

  // postMarketItem(item: ) {

  // }

  get marketItems() {
    return this.marketItemsList;
  }

  set marketItems(marketItems: IMarketItem[]) {
    this.marketItemsList = marketItems;
  }

  manageMarketItems(item: IMarketItem, enabled: boolean) {
    
  }

}
