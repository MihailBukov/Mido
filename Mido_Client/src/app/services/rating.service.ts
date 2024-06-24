import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RatingComponent } from '../components/rating/rating.component';
import { Observable } from 'rxjs';
import { CommentComponent } from '../components/comment/comment.component';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private baseUrl = 'localhost:8080/api/rating';

  constructor(private http: HttpClient) { }

  createRating(rating: RatingComponent): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}`, rating);
  }

  editRating(id: number, rating: RatingComponent): Observable<RatingComponent> {
    return this.http.patch<CommentComponent>(`${this.baseUrl}/${id}`, rating);
  }

  deleteRating(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
