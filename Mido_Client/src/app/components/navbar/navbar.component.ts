import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  username: string | undefined = '';
  role: string | undefined = '';

  constructor(private router: Router, private authService: AuthService) {
    this.username = this.authService.currentUserValue?.username;
    this.role = this.authService.currentUserValue?.role;
  }

  isLoggedIn(): boolean {
    return this.authService.isAuthenticated();
  }

  logout(): void {
    this.authService.logout();
  }
}
