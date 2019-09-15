import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExtensionWrapperComponent } from './extension-wrapper.component';

describe('ExtensionWrapperComponent', () => {
  let component: ExtensionWrapperComponent;
  let fixture: ComponentFixture<ExtensionWrapperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExtensionWrapperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExtensionWrapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
