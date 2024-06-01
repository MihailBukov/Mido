import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth.service';

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
    const { username, password } = this.loginForm.value;
    /*
    * get the user by his name with auth service method
    */
    this.authService.getUserByUsername(username as string).subscribe (
      response => {
        if(response.length > 0 && response[0].password === password) {
          console.log("Successfully loged in.")//should be a display message
          /*
          * navigate to next page
          */
          this.router.navigate(['home']);
        }
        else {
          sessionStorage.setItem('username', username as string)
          console.log("Username or password wrong.")//should be a display message
        }
      },
      error => {
        console.log(error);
      }
    )
  }
}
