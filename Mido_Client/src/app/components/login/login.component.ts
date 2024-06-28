import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import {AuthenticationRequest} from "../../models/requests/authentication-request";
import {take} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  /*
  * creating login form
  */
  loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private authService: AuthService,
  private router: Router) {

  }

  ngOnInit(): void {

  }

  getUsername() {
    return this.loginForm.controls['username'];
  }

  getPassword() {
    return this.loginForm.controls['password'];
  }

  login() {
    const username = this.getUsername().value;
    const password = this.getPassword().value;

    if (username && password) {
      const request: AuthenticationRequest = {
        username: username,
        password: password
      };

      this.authService.login(request).pipe(take(1)).subscribe({
        next: () => {
          this.authService.extractUserInformationFromJwtToken();
          this.router.navigate(['home']);
        },
        error: (error) => {
          console.log(error);
        }
      })
    } else {
      console.log("Username and password cannot be null."); // Should be a display message
    }
  }}
