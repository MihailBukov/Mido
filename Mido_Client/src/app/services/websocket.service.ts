import { Injectable } from '@angular/core';
import { Client, Message, Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private stompClient: Client;
  private messageSubject = new Subject<string>();

  constructor() {
    this.stompClient = new Client({
      brokerURL: 'ws://localhost:8080/ws',
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
      debug: (str) => {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    this.stompClient.onConnect = () => {
      this.stompClient.subscribe('/user/queue/messages', (message: Message) => {
        this.messageSubject.next(message.body);
      });
    };

    this.stompClient.activate();
  }

  sendMessage(destination: string, body: any) {
    this.stompClient.publish({
      destination: destination,
      body: JSON.stringify(body)
    });
  }

  getMessages(): Observable<string> {
    return this.messageSubject.asObservable();
  }
}