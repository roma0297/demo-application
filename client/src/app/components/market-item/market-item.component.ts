import { Component, OnInit, Input } from '@angular/core';

import { MarketItemService } from '../../services/market-item.service';
import { IMarketItem } from './../../../common/types/market-place.d';


@Component({
  selector: 'app-market-item',
  templateUrl: './market-item.component.html',
  styleUrls: ['./market-item.component.scss']
})
export class MarketItemComponent implements OnInit {
  animationFlag: boolean = false;
  
  @Input() marketItem: IMarketItem;

  constructor(private marketItemService: MarketItemService) { }

  ngOnInit() {
    this.animationFlag = this.marketItem.isShown;
  }

  addMarketItem(marketItem: IMarketItem) {
    this.marketItemService.manageMarketItems(marketItem, true);
  }

}
