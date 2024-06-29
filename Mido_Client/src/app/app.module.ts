import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AdvertisementComponent } from './components/advertisement/advertisement.component';
import { AdvertisementsComponent } from './components/advertisements/advertisements.component';
import { ClientComponent } from './components/client/client.component';
import { HomeComponent } from './components/home/home.component';
import { PetShelterComponent } from './components/pet-shelter/pet-shelter.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CreateAdvertisementComponent } from './components/create-advertisement/create-advertisement.component';
import { ChatComponent } from './components/chat/chat.component';
import { ProfileComponent } from './components/profile/profile.component'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {JwtInterceptor} from "./interceptors/jwt_interceptor";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdvertisementComponent,
    AdvertisementsComponent,
    ClientComponent,
    HomeComponent,
    PetShelterComponent,
    RegisterComponent,
    NavbarComponent,
    CreateAdvertisementComponent,
    ChatComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
