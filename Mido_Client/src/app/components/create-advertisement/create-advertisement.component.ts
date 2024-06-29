import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from '@progress/kendo-angular-notification';
import { Advertisement } from 'src/app/models/Advertisement';
import { AdvertisementService } from 'src/app/services/advertisement.service';

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
    photo: ['']
  });

  constructor(private fb: FormBuilder, private adService: AdvertisementService,
    private router: Router, private notificationService: NotificationService) {}

  ngOnInit(): void {
    
  }

  createAd() {
    const ad = { ...this.createAdForm.value };
    this.adService.createAd(ad as Advertisement).subscribe(
      response => {
        this.notificationService.show({
          content: 'Advertisement is created.',
          type: { style: 'success', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
        this.router.navigate(['home']);
      },
      error => {
        this.notificationService.show({
          content: 'There was an error while creating the advertisement',
          type: { style: 'error', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
      }
    )
  }
}
