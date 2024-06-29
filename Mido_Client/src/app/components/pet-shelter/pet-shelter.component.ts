import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '@progress/kendo-angular-notification';
import { PetShelter } from 'src/app/models/PetShelter';
import { Role } from 'src/app/models/Role';
import { Status } from 'src/app/models/Status';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-pet-shelter',
  templateUrl: './pet-shelter.component.html',
  styleUrls: ['./pet-shelter.component.css']
})
export class PetShelterComponent implements OnInit{
  petShelterForm = this.fb.group({
    id: [''],
    name: ['', Validators.required],
    country: ['', Validators.required],
    city: ['', Validators.required],
    capacity: [0, Validators.required],
    address: ['', Validators.required],
    description: [''],
    photo: [''],
    isVerified: [true],
    user: this.fb.group({
      username: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required, Validators.min(5)],
      role: [Role.PET_SHELTER, Validators.required],
      status: [Status.OFFLINE, Validators.required]
    })
  })

  constructor(private fb: FormBuilder, private authService: AuthService,
    private router: Router, private route: ActivatedRoute, private notificationService: NotificationService) {}

  ngOnInit(): void {

  }

  createShelter() {
    const petShelter = { ...this.petShelterForm.value };
    this.authService.createShelter(petShelter as PetShelter).subscribe(
      response => {
        this.notificationService.show({
          content: 'Pet Shelter has been created',
          type: { style: 'success', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
        this.router.navigate(['home']);
      },
      error => {
        this.notificationService.show({
          content: 'There was an error while creating the pet shelter',
          type: { style: 'error', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
      }
    )
  }
}
