import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AdvertisementService } from 'src/app/services/advertisement.service';
import { Advertisement } from 'src/app/models/Advertisement';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  protected searchForm = this.fb.group({
    country: [''],
    city: [''],
    breed: [''],
    age: [''],
    gender: [''],
  });

  countries: string[] = ['Bulgaria'];
  cities: string[] = ['Sofia', 'Plovdiv', 'Burgas'];
  breeds: string[] = ['German Shepherd', 'Labrador', 'Staffordshire Bull Terrier', 'Jack Russell Terrier'];
  ages: string[] = ['0-3', '3-6','6-9', 'older'];
  genders: string[] = ['Male', 'Female'];

  constructor(private fb: FormBuilder, private adService: AdvertisementService,
    private router: Router) {}

  ngOnInit(): void {
  }

  searchAds() {
    const search = { ...this.searchForm.value };
    this.adService.searchAds(search.country ?? '', search.city ?? '', search.breed ?? '',
       search.age ?? '', search.gender ?? '').subscribe(
        (response: Advertisement[]) => {
          const ads = response.values;
          this.router.navigate(['advertisements', ads]);
        },
      error => {
        console.log(error)//a display message can also be added
      }
    )
  }
}
