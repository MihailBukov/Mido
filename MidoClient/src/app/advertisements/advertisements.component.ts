import { Component } from '@angular/core';

@Component({
  selector: 'app-advertisements',
  standalone: true,
  imports: [],
  templateUrl: './advertisements.component.html',
  styleUrl: './advertisements.component.css'
})
export class AdvertisementsComponent {
  ads: AdvertisementsComponent[];

  constructor(ads: AdvertisementsComponent[]) {
    this.ads = ads;
  }
}
