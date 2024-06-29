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
  cities: string[] = ['Sofia', 'Plovdiv', 'Varna', 'Burgas'];
  breeds: string[] = ['German Shepherd', 'Labrador', 'Staffordshire Bull Terrier', 'Jack Russell Terrier'];
  ages: string[] = ['0-3', '3-6','6-9', 'older'];
  genders: string[] = ['Male', 'Female'];

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
  }

  searchAds() {
    const search = { ...this.searchForm.value };
    this.router.navigate(['/advertisements'], {
      queryParams: {
        country: this.searchForm.get('country')?.value ?? '',
        city: this.searchForm.get('city')?.value ?? '',
        breed: this.searchForm.get('breed')?.value ?? '',
        age: this.searchForm.get('age')?.value ?? '',
        gender: this.searchForm.get('gender')?.value ?? ''
      }
    });
  }
}
