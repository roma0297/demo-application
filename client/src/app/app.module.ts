import { ErrorMessageComponent } from './shared/components/error/error.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTooltipModule } from '@angular/material/tooltip';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatTooltipModule,
    BrowserAnimationsModule
    // MatSortModule,
    // MatTableModule,
    // MatPaginatorModule,
    // MatProgressSpinnerModule,
    // MatDialogModule,
    // MatExpansionModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
