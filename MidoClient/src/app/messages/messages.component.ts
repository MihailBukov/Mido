import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { UserComponent } from '../user/user.component';

@Component({
  selector: 'app-messages',
  standalone: true,
  imports: [],
  templateUrl: './messages.component.html',
  styleUrl: './messages.component.css'
})
export class MessagesComponent {
  id?: number;
  message: string;
  sender: UserComponent;
  receiver: UserComponent;
  messages: MessagesComponent[] = [];

  constructor(message: string, sender: UserComponent, receiver: UserComponent, id?: number){
    this.id = id;
    this.message = message;
    this.sender = sender;
    this.receiver = receiver;
  }

  ngOnInit() {
    // everytime the component is used this method will start
  }

  sendMessage() {
    //example
    if (this.message.trim() === '') return;

    const newMessage = new MessagesComponent(this.message, this.sender, this.receiver);

    this.messages.push(newMessage);
    this.message = '';
  }
}
