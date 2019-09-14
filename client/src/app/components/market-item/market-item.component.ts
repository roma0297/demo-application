import { IMarketItem } from './../../../common/types/market-place.d';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-market-item',
  templateUrl: './market-item.component.html',
  styleUrls: ['./market-item.component.scss']
})
export class MarketItemComponent implements OnInit {
  @Input() marketItem: IMarketItem;

  constructor() { }

  ngOnInit() {
  }

  addMarketItem(marketItem: IMarketItem) {
    console.log('this is market item', marketItem);
    marketItem.enabled = !marketItem.enabled;
  }

}
