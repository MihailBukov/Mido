import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from '@progress/kendo-angular-notification';
import { Role } from 'src/app/models/Role';
import { Status } from 'src/app/models/Status';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  /*
  * creating form for registering
  */
  registerForm = this.fb.group({
    username: ['', Validators.required],
    email: ['', Validators.required, Validators.email],
    password: ['', Validators.required, Validators.min(5)],
    role: ['', Validators.required],
    status: [Status.OFFLINE]
  })

  constructor(private fb: FormBuilder, private authService: AuthService,
    private router: Router, private notificationService: NotificationService) {

  }

  ngOnInit(): void {
    
  }

  getUsername() {
    return this.registerForm.controls['username'];
  }

  getEmail() {
    return this.registerForm.controls['email'];
  }

  getPassword() {
    return this.registerForm.controls['password'];
  }

  register() {
    const userRegistered: User = {
        username: this.registerForm.get('username')?.value ?? '',
        email: this.registerForm.get('email')?.value ?? '',
        password: this.registerForm.get('password')?.value ?? '',
        role: this.registerForm.get('role')?.value === 'Client' ? Role.CLIENT : Role.PET_SHELTER,
        status: Status.OFFLINE
    };
    
    this.authService.register(userRegistered as User).subscribe(
      (response: any) => {
        this.notificationService.show({
          content: 'User has been registered',
          type: { style: 'success', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
        if(userRegistered.role === Role.CLIENT) {
          this.router.navigate(['client', userRegistered.username]);
        }
        else {
          this.router.navigate(['pet-shelter', userRegistered.username]);
        }
      },
      error => {
        this.notificationService.show({
          content: 'There was an error while registering the user',
          type: { style: 'error', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
      }
    )
  }
}
