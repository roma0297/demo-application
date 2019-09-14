import { Component, OnInit, Input } from '@angular/core';
import { TStep } from 'src/common/types/market-place';

@Component({
  selector: 'app-step',
  templateUrl: './step.component.html',
  styleUrls: ['./step.component.scss']
})
export class StepComponent implements OnInit {
  @Input() isVertical: boolean;
  @Input() lineWidth: number;
  @Input() step: TStep;

  constructor() { }

  ngOnInit() {
  }

}
