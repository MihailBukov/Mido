import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

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

  countries: string[] = ['Country1', 'Country2', 'Country3'];
  cities: string[] = ['City1', 'City2', 'City3'];
  breeds: string[] = ['Breed1', 'Breed2', 'Breed3'];
  ages: string[] = ['0-3', '3-6','6-9', 'older'];
  genders: string[] = ['Male', 'Female'];

  constructor(private fb: FormBuilder, private authService: AuthService,
    private router: Router) {}

  ngOnInit(): void {
  }

  searchAds() {
    const search = { ...this.searchForm.value };
    this.authService.searchAds(search.country ?? '', search.city ?? '', search.breed ?? '',
       search.age ?? '', search.gender ?? '').subscribe(
      response => {
        console.log("Pet Shelter successfully created!")//a display message can also be added
        this.router.navigate(['home']);
      },
      error => {
        console.log(error)//a display message can also be added
      }
    )
  }
}
