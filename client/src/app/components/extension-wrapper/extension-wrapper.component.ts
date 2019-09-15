import { Component, OnInit, Input } from '@angular/core';
import { TObject } from 'src/common/types/market-place';

@Component({
  selector: 'app-extension-wrapper',
  templateUrl: './extension-wrapper.component.html',
  styleUrls: ['./extension-wrapper.component.scss']
})
export class ExtensionWrapperComponent implements OnInit {

  @Input() config: TObject;

  constructor() { }

  ngOnInit() {
  }

}
