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
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      
    }
  }

  seeAd(ad: Advertisement): void {
    if(ad && ad.id) {
      this.adService.getAd(ad.id).subscribe(
        (response: Advertisement) => {
          this.router.navigate(['advertisement', ad.id]);
        },
      error => {
        console.log(error)//a display message can also be added
      }
      );
    } else {
      console.log('Invalid advertisement ID');
    }
  }
    
}
