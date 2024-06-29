import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Chat } from '../models/Chat';
import { Observable } from 'rxjs';
import { Message } from '../models/Message';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private baseUrl = 'http://localhost:8080/api/chat';

  constructor(private http: HttpClient) { }

  getAllCurrentUserChats(username: string): Observable<Chat[]> {
    return this.http.get<Chat[]>(`${this.baseUrl}/${username}`);
  }

  getChatMessages(sender: string, receiver: string): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.baseUrl}/messeges/${sender}/${receiver}`);
  }
}
