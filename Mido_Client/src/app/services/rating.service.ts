import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserRating } from '../models/UserRating';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private baseUrl = 'localhost:8080/api/rating';

  constructor(private http: HttpClient) { }

  createRating(rating: UserRating): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}`, rating);
  }

  editRating(id: number, rating: UserRating): Observable<UserRating> {
    return this.http.patch<UserRating>(`${this.baseUrl}/${id}`, rating);
  }

  deleteRating(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
