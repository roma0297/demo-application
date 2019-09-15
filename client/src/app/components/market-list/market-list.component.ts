import { AccountService } from './../../services/account.service';
import { Component, OnInit, OnDestroy, Inject, Injector, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';

import { IMarketItem } from '../../../common/types/market-place';
// import { CONTAINER_DATA } from 'src/app/services/account.service';

@Component({
  selector: 'app-market-list',
  templateUrl: './market-list.component.html',
  styleUrls: ['./market-list.component.scss']
})
export class MarketListComponent implements OnInit, OnDestroy {

  marketItems: IMarketItem[] = this.accountService.marketItems;
  private subscriptions: Subscription[] = [];

  constructor(private accountService: AccountService) { }

  ngOnInit() {
    const subscription = this.accountService.marketItemsSubject.subscribe((items: IMarketItem[]) => this.marketItems = items);
    this.subscriptions.push(subscription);
  }
  ngOnDestroy() {
    this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
  }

}
