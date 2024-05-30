import { Component } from '@angular/core';
import { Client } from '../../models/Client';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css'
})
export class ClientComponent {
  client: Client

    message() {
      // functionallity to message an user
    }

    rate() {
      // functionallity to rate an user
    } 

    comment() {
      // functionallity to leave a comment about the user
    }
}
