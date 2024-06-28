import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { BehaviorSubject, Observable } from 'rxjs';
import { PetShelter } from '../models/PetShelter';
import { Client } from '../models/Client';
import { AuthenticationRequest } from "../models/requests/authentication-request";
import { jwtDecode } from "jwt-decode";
import { CookieService } from "ngx-cookie-service";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  /*
  * here are all the services to connect with the backend
  */
  private baseUrl = 'http://localhost:8080/api';//should be something else
  private currentUser: BehaviorSubject<User | null>;

  constructor(private http: HttpClient, private cookieService: CookieService, private router: Router) {
    const fullToken = this.cookieService.get('access_token');

    if (fullToken === "") {
      this.currentUser = new BehaviorSubject<User | null>(null);
    } else {
      const decodedToken = jwtDecode(fullToken);
      this.currentUser = new BehaviorSubject<User>({
        id: decodedToken['id'],
        role: decodedToken['role'],
        username: decodedToken['sub']
      })
    }
  }

  get currentUserValue(): User | null {
    return this.currentUser.value;
  }

  register(user: User) {
    return this.http.post('${this.baseUrl}/users', user); //not sure about the url
  }

  login(request: AuthenticationRequest): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/auth/authenticate`, request);
  }

  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/users?username=${username}`);
  }

  createShelter(shelter: PetShelter) {
    return this.http.post('${this.baseUrl}/shelter', shelter); //this is made up, should be discussed
  }

  createClient(client: Client) {
    return this.http.post('${this.baseUrl}/client', client); //this is made up, should be discussed
  }

  isAuthenticated(): boolean {
    return !!this.currentUserValue;
  }

  logout() {
    this.currentUser = new BehaviorSubject<User | null>(null);
    this.router.navigate(['login']);
  }
}
