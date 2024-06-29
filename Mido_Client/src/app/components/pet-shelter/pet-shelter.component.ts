import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PetShelter } from 'src/app/models/PetShelter';
import { Role } from 'src/app/models/Role';
import { Status } from 'src/app/models/Status';
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
    private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {

  }

  createShelter() {
    const petShelter = { ...this.petShelterForm.value };
    this.authService.createShelter(petShelter as PetShelter).subscribe(
      response => {

        this.router.navigate(['home']);
      },
      error => {

      }
    )
  }
}
