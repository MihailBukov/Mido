import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { Observable } from 'rxjs';
import { PetShelter } from '../models/PetShelter';
import { Client } from '../models/Client';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  /*
  * here are all the services to connect with the backend
  */
  private baseUrl = '';//should be something else
  private loggedIn = false;

  constructor(private http: HttpClient) { }

  register(user: User) {
    this.loggedIn = true;
    return this.http.post('${this.baseUrl}/users', user); //not sure about the url
  }

  getUserByUsername(username: string): Observable<User> {
    this.loggedIn = true;
    return this.http.get<User>(`${this.baseUrl}/users?username=${username}`);
  }

  createShelter(shelter: PetShelter) {
    return this.http.post('${this.baseUrl}/shelter', shelter); //this is made up, should be discussed
  }

  createClient(client: Client) {
    return this.http.post('${this.baseUrl}/client', client); //this is made up, should be discussed
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }

  logout() {
    this.loggedIn = false;

  }
}
