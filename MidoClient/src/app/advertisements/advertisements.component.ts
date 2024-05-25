import { OnInit } from '@angular/core';
import { Component } from '@angular/core';

@Component({
  selector: 'app-advertisements',
  standalone: true,
  imports: [],
  templateUrl: './advertisements.component.html',
  styleUrl: './advertisements.component.css'
})
export class AdvertisementsComponent {
  ads: AdvertisementsComponent[] = [];

  constructor(ads: AdvertisementsComponent[]) {
    this.ads = ads;
  }

  ngOnInit() {
    //job that is done everytime the component is loaded
  }

  searchAdvertisements(){
    //search for advertisements
  }
}
