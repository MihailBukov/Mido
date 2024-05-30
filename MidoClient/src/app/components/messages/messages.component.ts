import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { UserComponent } from '../user/user.component';
import { MessageDto } from '../../models/dtos/MessageDto';

@Component({
  selector: 'app-messages',
  standalone: true,
  imports: [],
  templateUrl: './messages.component.html',
  styleUrl: './messages.component.css'
})
export class MessagesComponent {
  message: MessageDto;
  
  ngOnInit() {
    // everytime the component is used this method will start
  }

  sendMessage() {

  }
}
