import { NgModule } from '@angular/core';
import {Routes, RouterModule, Router} from '@angular/router';
import { RegisterPageComponent } from './views/register-page/register-page.component';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { LoginPageComponent } from './views/login-page/login-page.component';


const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginPageComponent},
  {path: 'register',  component: RegisterPageComponent},
  {path: 'home', component: HomePageComponent}
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
