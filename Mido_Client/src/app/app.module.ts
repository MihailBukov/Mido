import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AdvertisementComponent } from './components/advertisement/advertisement.component';
import { AdvertisementsComponent } from './components/advertisements/advertisements.component';
import { ClientComponent } from './components/client/client.component';
import { HomeComponent } from './components/home/home.component';
import { MessagesComponent } from './components/messages/messages.component';
import { PetShelterComponent } from './components/pet-shelter/pet-shelter.component';
import { RegisterComponent } from './components/register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CreateAdvertisementComponent } from './components/create-advertisement/create-advertisement.component';
import { CommentComponent } from './components/comment/comment.component';
import { RatingComponent } from './components/rating/rating.component';
import { AdvertisementCommentComponent } from './components/advertisement-comment/advertisement-comment.component';
import { ChatComponent } from './components/chat/chat.component';
import { NotificationComponent } from './components/notification/notification.component'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdvertisementComponent,
    AdvertisementsComponent,
    ClientComponent,
    HomeComponent,
    MessagesComponent,
    PetShelterComponent,
    RegisterComponent,
    NavbarComponent,
    CreateAdvertisementComponent,
    CommentComponent,
    RatingComponent,
    AdvertisementCommentComponent,
    ChatComponent,
    NotificationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
