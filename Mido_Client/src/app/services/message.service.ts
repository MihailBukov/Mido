import { Injectable } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private stompClient: any;
  private messageSubject = new Subject<any>();

  constructor() {
    this.initializeWebSocketConnection();
  }

  private initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8080/ws';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    this.stompClient.connect({}, function(frame: any) {
      _this.stompClient.subscribe('/user/queue/messages', (message: any) => {
        if (message.body) {
          _this.messageSubject.next(JSON.parse(message.body));
        }
      });
    });
  }

  public sendMessage(message: any) {
    this.stompClient.send('/app/create', {}, JSON.stringify(message));
  }

  public getMessages(): Observable<any> {
    return this.messageSubject.asObservable();
  }
}
