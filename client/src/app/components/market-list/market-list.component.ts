import { Component, OnInit, OnDestroy, Inject, Injector } from '@angular/core';
import { Subscription } from 'rxjs';

import { IMarketItem } from '../../../common/types/market-place';
import { MarketItemService } from '../../services/market-item.service';
import { CONTAINER_DATA } from 'src/app/services/account.service';

@Component({
  selector: 'app-market-list',
  templateUrl: './market-list.component.html',
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
