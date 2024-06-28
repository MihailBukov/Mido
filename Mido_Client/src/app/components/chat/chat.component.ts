import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Chat } from 'src/app/models/Chat';
import { Message } from 'src/app/models/Message';
import { ChatService } from 'src/app/services/chat.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit{
  chat!: Chat;
  messeges: Message[] = [];
  allChats: Chat[] = [];
  newMessage!: Message;
  newMessageText: string = '';

  constructor(private messageService: MessageService, private chatService: ChatService,
    private route: ActivatedRoute,  private router: Router) {   }

  ngOnInit(): void {
    this.chatService.getAllCurrentUserChats("").subscribe(//here instead of "" we should have the current user's username
      (response: Chat[]) => {
        this.allChats = response;
      },
      error => {
        console.error(error);
      }
    );

    this.chat = this.allChats.find(chat => chat.receiverUsername === this.route.snapshot.params['username']) || {
      chatName: '',
      senderUsername: '',
      receiverUsername: ''
    };

    if(this.chat) {
      this.chatService.getChatMessages(this.chat.senderUsername, this.chat.receiverUsername).subscribe(
        (response: Message[]) => {
          this.messeges = response;
        },
        error => {
          console.error(error);
        }
      );
    }
  }

  loadChat(receiverUsername: string) {
    this.router.navigate(['chat', receiverUsername])
  }

  sendMessage() {
    this.newMessage = {
      chatName: this.chat.chatName,
      senderUsername: this.chat.senderUsername,
      receiverUsername: this.chat.receiverUsername,
      text: this.newMessageText
    }

    this.messageService.sendMessage(this.newMessage);
    this.router.navigate(['chat', this.chat.receiverUsername]);// here username represents the receiver in the new chat
  }
}
