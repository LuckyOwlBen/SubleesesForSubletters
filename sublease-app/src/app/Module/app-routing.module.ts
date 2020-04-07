import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Routes, RouterModule} from '@angular/router';
import { AuthenticateComponent } from '../Components/Forms/authenticate/authenticate.component';


const routes: Routes = [
  {path: '', redirectTo: '/register', pathMatch: 'full'},
  {path: '/register',  component: AuthenticateComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class AppRoutingModule { }
