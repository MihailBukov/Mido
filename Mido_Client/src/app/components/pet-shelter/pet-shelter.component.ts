import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PetShelter } from 'src/app/models/PetShelter';
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
    photo: [0],
    isVerified: [true],
    user: this.fb.group({
      username: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required, Validators.min(5)],
      role: ['', Validators.required]
    })
  })

  constructor(private fb: FormBuilder, private authService: AuthService,
    private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.authService.getUserByUsername(this.route.snapshot.params['username']).subscribe((user: User) => {
      this.petShelterForm.get('user')?.patchValue(user);
    });
  }

  getName() {
    return this.petShelterForm.controls['name'];
  }

  getCountry() {
    return this.petShelterForm.controls['country'];
  }

  getCity() {
    return this.petShelterForm.controls['city'];
  }

  getCapacity() {
    return this.petShelterForm.controls['capacity'];
  }

  getAddress() {
    return this.petShelterForm.controls['address'];
  }

  getDescription() {
    return this.petShelterForm.controls['description'];
  }

  getPhoto() {
    return this.petShelterForm.controls['photo'];
  }

  getVerification() {
    return this.petShelterForm.controls['isVerified'];
  }

  createShelter() {
    const petShelter = { ...this.petShelterForm.value };
    this.authService.createShelter(petShelter as PetShelter).subscribe(
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