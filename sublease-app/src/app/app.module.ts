import { MaterialModule } from './Materials/MaterialsModule';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { RegisterFormComponent } from './Components/Forms/register-form/register-form.component';
import { AppRoutingModule } from './app-routing.module';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { LoginFormComponent } from './Components/forms/login-form/login-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './Components/header/header.component';
import { RegisterPageComponent } from './views/register-page/register-page.component';
import { LoginPageComponent } from './views/login-page/login-page.component';
import { ApiLoginComponent } from './views/api-login/api-login.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterFormComponent,
    HomePageComponent,
    LoginFormComponent,
    HeaderComponent,
    RegisterPageComponent,
    LoginPageComponent,
    ApiLoginComponent
  ],
  imports: [
    MaterialModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
