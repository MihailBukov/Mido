import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
    role: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private authService: AuthService,
    private router: Router) {

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
    const userRegistered = { ...this.registerForm.value };
    this.authService.register(userRegistered as User).subscribe(
      response => {
        console.log("Registration successfull!")//a display message can also be added
        if(userRegistered.role === "Client") {
          /*
          * go to page for creating a client
          */
          this.router.navigate(['create_client']);
        }
        else {
          /*
          * go to page for creating a pet shelter
          */
          this.router.navigate(['create_pet_shelter']);
        }
      },
      error => {
        console.log(error)//a display message can also be added
      }
    )
  }
}
