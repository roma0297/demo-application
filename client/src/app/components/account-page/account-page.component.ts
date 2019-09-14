import { Component, OnInit } from '@angular/core';
import { ComponentPortal } from '@angular/cdk/portal';

import { TTab } from './../../../common/types/market-place.d';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrls: ['./account-page.component.scss']
})
export class AccountPageComponent {
  // userSettingsPortal: any;

  tabs: TTab[] = [
    {
      label: 'Аналитика',
      content: new ComponentPortal(NavigationComponent)
    },
    {
      label: 'Аналитика',
      content: new ComponentPortal(NavigationComponent)
    },
    {
      label: 'Аналитика',
      content: new ComponentPortal(NavigationComponent)
    },
    {
      label: 'Аналитика',
      content: new ComponentPortal(NavigationComponent)
    }
  ];

}
