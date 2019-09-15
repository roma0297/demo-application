import { CONTAINER_DATA } from '../../configs/tokens';
// import { CONTAINER_DATA } from '../../services/account.service';
import { Component, OnInit, Input, Injector } from '@angular/core';
import { TObject } from 'src/common/types/market-place';

@Component({
  selector: 'app-extension-wrapper',
  templateUrl: './extension-wrapper.component.html',
  styleUrls: ['./extension-wrapper.component.scss']
})
export class ExtensionWrapperComponent implements OnInit {

  config: TObject = {
    fields: []
  };

  constructor(private injector: Injector) { }

  ngOnInit() {
    this.config = this.injector.get(CONTAINER_DATA);
  }

}
