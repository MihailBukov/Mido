import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Advertisement } from 'src/app/models/Advertisement';
import { User } from 'src/app/models/User';
import { AdvertisementService } from 'src/app/services/advertisement.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-create-advertisement',
  templateUrl: './create-advertisement.component.html',
  styleUrls: ['./create-advertisement.component.css']
})
export class CreateAdvertisementComponent implements OnInit{

  currentUser: User | null;

  createAdForm = this.fb.group({
    title: ['', Validators.required],
    description: [''],
    dogName: ['', Validators.required],
    dogAge: [0 , Validators.required],
    dogKg: [0 , Validators.required],
    dogBreed: ['', Validators.required],
    dogGender: ['', Validators.required],
    dogColor: ['', Validators.required],
    country: ['', Validators.required],
    city: ['', Validators.required],
    createdBy: [''],
    photo: [null]
  });

  constructor(private fb: FormBuilder, private adService: AdvertisementService,
    private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.currentUser = this.authService.currentUserValue;
  }

  createAd() {
    const ad = { ...this.createAdForm.value };
    ad.createdBy = this.currentUser?.username;
    this.adService.createAd(ad as Advertisement).subscribe(
      response => {
        this.router.navigate(['/home']);
      },
      error => {
      }
    )
  }

  onFileChange(event: any) {
    const file = event.target.files[0];
    this.createAdForm.patchValue({
      photo: file
    });
  }
}
