import { NgModule } from '@angular/core';
import {Routes, RouterModule, Router} from '@angular/router';
import { RegisterPageComponent } from './views/register-page/register-page.component';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { LoginPageComponent } from './views/login-page/login-page.component';
import { ApiLoginPageComponent } from './views/api-login-page/api-login-page.component';
import { ApiRegisterPageComponent } from './views/api-register-page/api-register-page.component';

const routes: Routes = [
  {path: '', redirectTo: 'apiLogin', pathMatch: 'full'},
  {path: 'googleLogin', component: LoginPageComponent},
  {path: 'googleRegister',  component: RegisterPageComponent},
  {path: 'home', component: HomePageComponent},
  {path: 'apiLogin', component: ApiLoginPageComponent},
  {path: 'apiRegister', component: ApiRegisterPageComponent}
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
