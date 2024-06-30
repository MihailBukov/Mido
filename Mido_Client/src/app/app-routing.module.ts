import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdvertisementComponent } from './components/advertisement/advertisement.component';
import { AdvertisementsComponent } from './components/advertisements/advertisements.component';
import { AuthGuard } from './guards/auth.guard';
import { CreateAdvertisementComponent } from './components/create-advertisement/create-advertisement.component';
import {ProfileComponent} from "./components/profile/profile.component";

/*
* routes represent all pages we can go to
*/
const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard]
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
    path: 'profile/:username/:role',
    component: ProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'advertisement/:id',
    component: AdvertisementComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'advertisements',
    component: AdvertisementsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'create-advertisement',
    component: CreateAdvertisementComponent,
    canActivate: [AuthGuard]
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
