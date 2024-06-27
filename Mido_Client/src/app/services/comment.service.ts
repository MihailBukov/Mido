import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserComment } from '../models/UserComment';
@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl = 'localhost:8080/api/comment';

  constructor(private http: HttpClient) { }

  getComment(id: number): Observable<UserComment> {
    return this.http.get<UserComment>(`${this.baseUrl}/${id}`); 
  }

  createComment(comment: UserComment): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}`, comment);
  }

  editComment(id: number, comment: UserComment): Observable<UserComment> {
    return this.http.patch<UserComment>(`${this.baseUrl}/${id}`, comment);
  } 

  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
