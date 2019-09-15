import { Component, OnInit, Input } from '@angular/core';

import { IMarketItem } from './../../../common/types/market-place.d';
import { AccountService } from './../../services/account.service';

@Component({
  selector: 'app-market-item',
  templateUrl: './market-item.component.html',
  styleUrls: ['./market-item.component.scss']
})
export class MarketItemComponent implements OnInit {
  animationFlag: boolean = false;
  
  @Input() marketItem: IMarketItem;

  constructor(private accountService: AccountService) { }

  ngOnInit() {
    this.animationFlag = this.marketItem.isShown;
  }

  addMarketItem(marketItem: IMarketItem) {
    // this.accountService.manageMarketItems(marketItem, true);
    this.accountService.addMarketItem(marketItem);
  }

}
