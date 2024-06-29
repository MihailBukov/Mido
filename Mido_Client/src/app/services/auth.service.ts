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
import {Role} from "../models/Role";
import {RegisterUserRequest} from "../models/requests/register-user";

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
    this.currentUser = new BehaviorSubject<User | null>(null);
    this.extractUserInformationFromJwtToken();
  }

  extractUserInformationFromJwtToken() {
    const fullToken = this.cookieService.get('access_token');

    if (fullToken === "") {
      this.currentUser = new BehaviorSubject<User | null>(null);
    } else {
      const decodedToken = jwtDecode<User>(fullToken);
      if (!decodedToken.id) {
        this.currentUser = new BehaviorSubject<User | null>(null);
      } else {
        this.currentUser = new BehaviorSubject<User | null>({
          id: decodedToken.id,
          role: decodedToken.role as Role,
          username: decodedToken['sub']
        })
      }
    }
  }

  get currentUserValue(): User | null {
    return this.currentUser.value;
  }

  register(user: string, image: File) {
    const formData: FormData = new FormData();
    formData.append('picture', image);
    formData.append('request', user);
    return this.http.post(`${this.baseUrl}/auth/register`, formData, {
      headers: {
        'Access-Control-Allow-Origin': '*'
      },
      withCredentials: true
    });
  }

  login(request: AuthenticationRequest): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/auth/authenticate`, request, {
      headers: {
        'Access-Control-Allow-Origin': '*'
      },
      withCredentials: true
    });
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
    this.cookieService.delete('access_token');
    this.router.navigate(['login']);
  }

  getClient(id: number): Observable<Client>{
    return this.http.get<Client>(`${this.baseUrl}/client/${id}`);
  }

  getPetShelter(id: number): Observable<PetShelter>{
    return this.http.get<PetShelter>(`${this.baseUrl}/pet-shelter/${id}`);
  }
}
