import { PortalInjector, ComponentPortal } from '@angular/cdk/portal';
import { Injectable, Injector } from '@angular/core';
// import { CONTAINER_DATA } from './account.service';
import { MarketListComponent } from '../components/market-list/market-list.component';
import { CONTAINER_DATA } from '../configs/tokens';

@Injectable({
  providedIn: 'root'
})
export class TabsService {

  constructor(private injector: Injector) { }

  // shot myself in the leg i called it
  generatePortal(tabName: string, options: any) {
    switch (tabName) {
      case 'marketList':
      return new ComponentPortal(MarketListComponent);
    }
  }

  createInjector(dataToPass): PortalInjector {
    const injectorTokens = new WeakMap();
    injectorTokens.set(CONTAINER_DATA, dataToPass);
    return new PortalInjector(this.injector, injectorTokens);
  }
}
