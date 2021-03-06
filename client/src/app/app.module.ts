import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PortalModule } from '@angular/cdk/portal';
import { ChartsModule } from 'ng2-charts';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { OverlayModule } from '@angular/cdk/overlay';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ErrorMessageComponent } from './shared/components/error/error.component';
import { DynamicFormComponent } from './shared/components/dynamic-form/dynamic-form.component';
import { InputComponent } from './shared/components/input/input.component';
import { SelectComponent } from './shared/components/select/select.component';
import { HintComponent } from './shared/components/hint/hint.component';
import { CheckboxComponent } from './shared/components/checkbox/checkbox.component';
import { HttpClientModule } from '@angular/common/http';
import { TextAreaComponent } from './shared/components/text-area/text-area.component';
import { ContentHeaderComponent } from './shared/components/content-header/content-header.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ProgressComponent } from './components/progress/progress.component';
import { StepComponent } from './components/step/step.component';
import { AccountPageComponent } from './components/account-page/account-page.component';
import { LoginComponent } from './components/login/login.component';
import { ButtonComponent } from './shared/components/button/button.component';
import { AnalyticsComponent } from './components/analytics/analytics.component';
import { MarketListComponent } from './components/market-list/market-list.component';
import { MarketItemComponent } from './components/market-item/market-item.component';
import { BASE_URL } from './configs/tokens';
import { CreditStockComponent } from './components/credit-stock/credit-stock.component';
import { TableComponent } from './components/table/table.component';
import { ExtensionWrapperComponent } from './components/extension-wrapper/extension-wrapper.component';

@NgModule({
  declarations: [
    AppComponent,
    DynamicFormComponent,
    InputComponent,
    SelectComponent,
    HintComponent,
    CheckboxComponent,
    ErrorMessageComponent,
    TextAreaComponent,
    ContentHeaderComponent,
    NavigationComponent,
    HomePageComponent,
    ProgressComponent,
    StepComponent,
    AccountPageComponent,
    LoginComponent,
    ButtonComponent,
    AnalyticsComponent,
    MarketListComponent,
    MarketItemComponent,
    CreditStockComponent,
    TableComponent,
    ExtensionWrapperComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatTooltipModule,
    BrowserAnimationsModule,
    MatTabsModule,
    PortalModule,
    ChartsModule,
    MatCardModule,
    OverlayModule,
    MatTableModule,
    MatPaginatorModule
  ],
  providers: [
    { provide: BASE_URL, useValue: 'http://localhost:8080' }
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    MarketListComponent,
    AnalyticsComponent,
    CreditStockComponent,
    AccountPageComponent,
    ExtensionWrapperComponent
  ]
})
export class AppModule { }
