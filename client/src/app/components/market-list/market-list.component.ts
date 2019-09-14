import { Component, OnInit, OnDestroy } from '@angular/core';

import { IMarketItem } from '../../../common/types/market-place';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { MarketItemService } from 'src/app/services/market-item.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-market-list',
  templateUrl: './market-list.component.html',
  animations: [
    trigger('showHide', [
      state('show', style({
        opacity: 1,
      })),
      state('hide', style({
        opacity: 0,
      })),
      transition('show => hide', [
        animate('1s')
      ]),
      transition('hide => show', [
        animate('1s')
      ]),
    ]),
  ],
  styleUrls: ['./market-list.component.scss']
})
export class MarketListComponent implements OnInit, OnDestroy {

  marketItems: IMarketItem[] = this.marketItemService.marketItems;
  private subscriptions: Subscription[] = [];

  constructor(private marketItemService: MarketItemService) { }

  ngOnInit() {
    const subscription = this.marketItemService.marketItemsSubject.subscribe((items: IMarketItem[]) => this.marketItems = items);
    this.subscriptions.push(subscription);
  }
  ngOnDestroy() {
    this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
  }

}
