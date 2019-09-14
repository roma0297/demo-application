import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketItemComponent } from './market-item.component';

describe('MarketItemComponent', () => {
  let component: MarketItemComponent;
  let fixture: ComponentFixture<MarketItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
