import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { AdvertisementDto } from '../../models/dtos/AdvertisementDto';

@Component({
  selector: 'app-advertisements',
  standalone: true,
  imports: [],
  templateUrl: './advertisements.component.html',
  styleUrl: './advertisements.component.css'
})
export class AdvertisementsComponent {
  ads: AdvertisementDto[] = [];

  ngOnInit() {
    //job that is done everytime the component is loaded
  }

  searchAdvertisements(){
    //search for advertisements
  }
}
