import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  /*
  * here are all the services to connect with the backend
  */
  private baseUrl = '';//should be something else

  constructor(private http: HttpClient) { }

  register(user: User) {
    return this.http.post('${this.baseUrl}/users', user); //not sure about the url
  }

  getUserByUsername(username: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/users?username=${username}`);
  }
}
