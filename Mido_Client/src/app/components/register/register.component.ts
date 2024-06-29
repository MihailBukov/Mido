import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Role } from "../../models/Role";
import {NotificationService} from "@progress/kendo-angular-notification";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {RegisterUserRequest} from "../../models/requests/register-user";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  selectedRole: string = '';

  constructor(
    private fb: FormBuilder,
    private notificationService: NotificationService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      role: ['', Validators.required],
      firstName: [''],
      middleName: [''],
      lastName: [''],
      age: [''],
      country: [''],
      city: [''],
      description: [''],
      name: [''],
      capacity: [''],
      address: [''],
      picture: [null]
    });

    this.registerForm.get('role')?.valueChanges.subscribe(role => {
      this.selectedRole = role;
      this.updateFormFields(role);
    });
  }

  updateFormFields(role: string): void {
    if (role === 'client') {
      this.registerForm.get('firstName')?.setValidators([Validators.required]);
      this.registerForm.get('lastName')?.setValidators([Validators.required]);
      this.registerForm.get('age')?.setValidators([Validators.required, Validators.min(0)]);
      this.registerForm.get('country')?.setValidators([Validators.required]);
      this.registerForm.get('city')?.setValidators([Validators.required]);

      this.registerForm.get('name')?.clearValidators();
      this.registerForm.get('capacity')?.clearValidators();
      this.registerForm.get('address')?.clearValidators();
    } else if (role === 'pet-shelter') {
      this.registerForm.get('name')?.setValidators([Validators.required]);
      this.registerForm.get('country')?.setValidators([Validators.required]);
      this.registerForm.get('city')?.setValidators([Validators.required]);
      this.registerForm.get('capacity')?.setValidators([Validators.required, Validators.min(0)]);
      this.registerForm.get('address')?.setValidators([Validators.required]);

      this.registerForm.get('firstName')?.clearValidators();
      this.registerForm.get('lastName')?.clearValidators();
      this.registerForm.get('age')?.clearValidators();
    }

    this.registerForm.get('firstName')?.updateValueAndValidity();
    this.registerForm.get('lastName')?.updateValueAndValidity();
    this.registerForm.get('age')?.updateValueAndValidity();
    this.registerForm.get('country')?.updateValueAndValidity();
    this.registerForm.get('city')?.updateValueAndValidity();
    this.registerForm.get('description')?.updateValueAndValidity();
    this.registerForm.get('name')?.updateValueAndValidity();
    this.registerForm.get('capacity')?.updateValueAndValidity();
    this.registerForm.get('address')?.updateValueAndValidity();
  }

  onFileChange(event: any) {
    const file = event.target.files[0];
    this.registerForm.patchValue({
      picture: file
    });
  }

  register() {
    let userRegistered: RegisterUserRequest = {
      username: this.registerForm.get('username')?.value ?? '',
      email: this.registerForm.get('email')?.value ?? '',
      password: this.registerForm.get('password')?.value ?? '',
      role: this.registerForm.get('role')?.value === 'client' ? Role.CLIENT : Role.PET_SHELTER,
      country: this.registerForm.get('country')?.value ?? '',
      city: this.registerForm.get('city')?.value ?? '',
      description: this.registerForm.get('description')?.value ?? ''
    };
    if (this.registerForm.get('role')?.value === 'client') {
      userRegistered = {
        ...userRegistered,
        firstName: this.registerForm.get('firstName')?.value ?? '',
        middleName: this.registerForm.get('middleName')?.value ?? '',
        lastName: this.registerForm.get('lastName')?.value ?? '',
        age: this.registerForm.get('age')?.value ?? 0,
      }
    } else {
      userRegistered = {
        ...userRegistered,
        name: this.registerForm.get('name')?.value ?? '',
        address: this.registerForm.get('address')?.value ?? '',
        capacity: this.registerForm.get('capacity')?.value ?? 0,
      }
    }

    this.authService.register(JSON.stringify(userRegistered), this.registerForm.get('picture')?.value ?? null).subscribe({
      next: () => {
        this.router.navigate(['home']);
      },
      error: () => {
        this.notificationService.show({
          content: 'There was an error while registering the user',
          type: { style: 'error', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
      }
    })
  }
}
