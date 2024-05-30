import { Message } from "../Message";
import { User } from "../User";

export class MessageDto {
    id?: number;
    message: string;
    sender: User;
    receiver: User;
  
    constructor(message: Message){
      this.id = message.id;
      this.message = message.message;
      this.sender = message.sender;
      this.receiver = message.receiver;
    }
}