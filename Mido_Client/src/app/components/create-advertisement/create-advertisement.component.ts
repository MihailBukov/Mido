import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Advertisement } from 'src/app/models/Advertisement';
import { AdvertisementService } from 'src/app/services/advertisement.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-create-advertisement',
  templateUrl: './create-advertisement.component.html',
  styleUrls: ['./create-advertisement.component.css']
})
export class CreateAdvertisementComponent implements OnInit{

  createAdForm = this.fb.group({
    title: ['', Validators.required],
    description: [''],
    dogName: ['', Validators.required],
    dogAge: [0 , Validators.required],
    dogKg: [0 , Validators.required],
    dogBreed: ['', Validators.required],
    dogGender: ['', Validators.required],
    dogColor: ['', Validators.required],
    photo: [0]
  });

  constructor(private fb: FormBuilder, private adService: AdvertisementService,
    private router: Router) {}

  ngOnInit(): void {
    
  }

  createAd() {
    const ad = { ...this.createAdForm.value };
    this.adService.createAd(ad as Advertisement).subscribe(
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
