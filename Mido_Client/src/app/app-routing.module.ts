import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdvertisementComponent } from './components/advertisement/advertisement.component';
import { AdvertisementsComponent } from './components/advertisements/advertisements.component';
import { ClientComponent } from './components/client/client.component';
import { MessagesComponent } from './components/messages/messages.component';
import { PetShelterComponent } from './components/pet-shelter/pet-shelter.component';
import { authGuard } from './guards/auth.guard';
import { UserComponent } from './components/user/user.component';

/*
* routes represent all pages we can go to
*/
const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'advertisement',
    component: AdvertisementComponent,
    canActivate: [authGuard]
  },
  {
    path: 'advertisements',
    component: AdvertisementsComponent,
    canActivate: [authGuard]
  },
  {
    path: 'client/:id',
    component: ClientComponent,
  },
  {
    path: 'messages',
    component: MessagesComponent,
    canActivate: [authGuard]
  },
  {
    path: 'pet-shelter/:id',
    component: PetShelterComponent,
  },
  {
    path: 'user',
    component: UserComponent,
    canActivate: [authGuard] // cannot access without login in
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
