import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/Client';
import { Role } from 'src/app/models/Role';
import { Status } from 'src/app/models/Status';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit{
  clientForm = this.fb.group({
    firstName: ['', Validators.required],
    middleName: ['', Validators.required],
    lastName: ['', Validators.required],
    age: [0, Validators.required],
    country: ['', Validators.required],
    city: ['', Validators.required],
    photo: [''],
    description: [''],
    user: this.fb.group({
      username: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required, Validators.min(5)],
      role: [Role.CLIENT, Validators.required],
      status: [Status.OFFLINE, Validators.required]
    })
  });

  constructor(private fb: FormBuilder, private authService: AuthService,
    private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.authService.getUserByUsername(this.route.snapshot.params['username']).subscribe((user: User) => {
      this.clientForm.get('user')?.patchValue(user);
    });
  }

  getFirstName() {
    return this.clientForm.controls['firstName'];
  }

  getMiddleName() {
    return this.clientForm.controls['middleName'];
  }

  getLastName() {
    return this.clientForm.controls['lastName'];
  }

  getAge() {
    return this.clientForm.controls['age'];
  }

  getCountry() {
    return this.clientForm.controls['country'];
  }

  getCity() {
    return this.clientForm.controls['city'];
  }

  getPhoto() {
    return this.clientForm.controls['photo'];
  }

  getDescription() {
    return this.clientForm.controls['description'];
  }

  createClient() {
    const client = { ... this.clientForm.value };
    this.authService.createClient(client as Client).subscribe(
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
