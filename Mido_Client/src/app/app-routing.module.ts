import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdvertisementComponent } from './components/advertisement/advertisement.component';
import { AdvertisementsComponent } from './components/advertisements/advertisements.component';
import { ClientComponent } from './components/client/client.component';
import { PetShelterComponent } from './components/pet-shelter/pet-shelter.component';
import { AuthGuard } from './guards/auth.guard';

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
    path: 'client/:id',
    component: ClientComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'pet-shelter/:id',
    component: PetShelterComponent,
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
