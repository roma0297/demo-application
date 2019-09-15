import { IMarketItem } from '../../../common/types/market-place';

export class MarketItem implements IMarketItem {
  id: number;
  itemName: string;
  name: string;
  subtitle: string;
  url: string;
  description: string;
  enabled: boolean;
  isShown: boolean;

  constructor(buildingOptions: IMarketItem) {
    this.id = buildingOptions.id;
    this.itemName = buildingOptions.itemName || '';
    this.name = buildingOptions.name;
    this.subtitle = buildingOptions.subtitle;
    this.url = buildingOptions.url;
    this.description = buildingOptions.description;
    this.enabled = buildingOptions.enabled;
    this.isShown = buildingOptions.isShown;
  }
}