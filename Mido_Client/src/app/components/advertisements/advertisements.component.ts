import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Advertisement } from 'src/app/models/Advertisement';
import { AdvertisementService } from 'src/app/services/advertisement.service';

@Component({
  selector: 'app-advertisements',
  templateUrl: './advertisements.component.html',
  styleUrls: ['./advertisements.component.css']
})
export class AdvertisementsComponent implements OnInit{
  ads: Advertisement[];

  constructor(private adService: AdvertisementService , private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const country = params['country'] || '';
      const city = params['city'] || '';
      const breed = params['breed'] || '';
      const age = params['age'] || '';
      const gender = params['gender'] || '';

      this.adService.searchAds(country, city, breed, age, gender).subscribe({
        next: (response: Advertisement[]) => {
          this.ads = response;
        },
        error: () => {

        }
      })
    })
  }

  seeAd(adId: number | undefined): void {
    if(adId) {
      this.router.navigate(['/advertisement', adId]);
    } else {
      console.log("Invalid ID");
    }

  }
}
