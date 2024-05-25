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
  text: string;
  sender: UserComponent;
  receiver: UserComponent;

  constructor(text: string, sender: UserComponent, receiver: UserComponent, id?: number){
    this.id = id;
    this.text = text;
    this.sender = sender;
    this.receiver = receiver;
  }
}
