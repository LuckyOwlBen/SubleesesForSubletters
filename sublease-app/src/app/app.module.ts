import { MaterialModule } from './Materials/MaterialsModule';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { AuthenticateComponent } from './Components/Forms/authenticate/authenticate.component';
import { AppRoutingModule } from './app-routing.module';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { LoginComponent } from './Components/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './views/header/header.component';
import { FooterComponent } from './views/footer/footer.component';
import { ListingDashboardComponent } from './Components/listing-dashboard/listing-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthenticateComponent,
    HomePageComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    ListingDashboardComponent
  ],
  imports: [
    MaterialModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
