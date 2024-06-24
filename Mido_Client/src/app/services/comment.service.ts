import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentComponent } from '../components/comment/comment.component';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl = 'localhost:8080/api/comment';

  constructor(private http: HttpClient) { }

  getComment(id: number): Observable<CommentComponent> {
    return this.http.get<CommentComponent>(`${this.baseUrl}/${id}`); 
  }

  createComment(comment: CommentComponent): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}`, comment);
  }

  editComment(id: number, comment: CommentComponent): Observable<CommentComponent> {
    return this.http.patch<CommentComponent>(`${this.baseUrl}/${id}`, comment);
  } 

  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
