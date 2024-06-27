import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Message } from '../models/Message';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private baseUrl = 'localhost:8080/api/message';

  constructor(private http: HttpClient) { }

  createMessage(message: Message): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/create`, message);
  }
}
