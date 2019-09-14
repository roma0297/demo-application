import { AccountPageComponent } from './components/account-page/account-page.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { HomePageComponent } from './components/home-page/home-page.component';
import { ProgressComponent } from './components/progress/progress.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {
    path: 'main',
    component: HomePageComponent
  },
  { 
    path: '',
    redirectTo: '/main',
    pathMatch: 'full'
  },
  {
    path: 'progress',
    component: ProgressComponent
  },
  {
    path: 'account',
    component: AccountPageComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
  // { path: 'hero/:id',      component: HeroDetailComponent },
  // {
  //   path: 'heroes',
  //   component: HeroListComponent,
  //   data: { title: 'Heroes List' }
  // },
  // { path: '',
  //   redirectTo: '/heroes',
  //   pathMatch: 'full'
  // },
  // { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
