import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Advertisement } from 'src/app/models/Advertisement';
import { AdvertisementService } from 'src/app/services/advertisement.service';

@Component({
  selector: 'app-advertisements',
  templateUrl: './advertisements.component.html',
  styleUrls: ['./advertisements.component.css']
})
export class AdvertisementsComponent implements OnInit{
  ads: Advertisement[] = [];

  constructor(private adService: AdvertisementService , private router: Router) {}

  ngOnInit(): void {

  }

  seeAd(ad: Advertisement): void {
    this.router.navigate(['advertisement', ad]);
  }
}
