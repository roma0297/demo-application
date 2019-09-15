import { MarketListComponent } from './../market-list/market-list.component';
import { Subscription } from 'node_modules/rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';

import { TTab } from './../../../common/types/market-place.d';
import { AccountService } from './../../services/account.service';

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrls: ['./account-page.component.scss']
})
export class AccountPageComponent implements OnInit, OnDestroy {

  tabs: TTab[] = this.accountService.tabs;
  private subscriptions: Subscription[] = [];

  constructor(private accountService: AccountService) {}

  ngOnInit() {
    const subscription = this.accountService.tabsSubject.subscribe((tabs: TTab[]) => this.tabs = tabs);
    this.subscriptions.push(subscription);
    this.accountService.setTablistConfig(MarketListComponent);
  }

  ngOnDestroy() {
    this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
  }

  removeTab(tab) {
    // To remove tab
    // console.log('hello', tab);
  }

}
