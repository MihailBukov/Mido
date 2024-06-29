import { Component, OnInit, resolveForwardRef } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/Client';
import { Message } from 'src/app/models/Message';
import { PetShelter } from 'src/app/models/PetShelter';
import { User } from 'src/app/models/User';
import { UserComment } from 'src/app/models/UserComment';
import { UserRating } from 'src/app/models/UserRating';
import { AuthService } from 'src/app/services/auth.service';
import { CommentService } from 'src/app/services/comment.service';
import { MessageService } from 'src/app/services/message.service';
import { RatingService } from 'src/app/services/rating.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  currentUser: User | null;
  username: string = '';
  role!: string;
  client!: Client;
  petShelter!: PetShelter;
  comment!: UserComment;
  rating!: UserRating;
  message!: Message;
  impressionFormActivated: boolean = false;
  messageFormActivated: boolean = false;

  constructor(private authService: AuthService, private route: ActivatedRoute, private router: Router, private fb: FormBuilder,
    private userCommentService: CommentService, private userRatingService: RatingService, private messageService: MessageService) {}

  impressionForm = this.fb.group({
      comment: [''],
      rating: 0
  });

  messageForm = this.fb.group({
    messageText: ['']
  });

  ngOnInit(): void {
    this.currentUser = this.authService.currentUserValue;
    this.username = this.route.snapshot.params['username'];

    if(this.username != this.currentUser?.username) {
      this.authService.getUserByUsername(this.username).subscribe(
        (response: User) => {
          this.role = !!response.role ? response.role.toString() : '';
        },
        error => {
          console.error(error);
        }
      );
    } else {
      this.role = !!this.currentUser?.role ? this.currentUser?.role.toString() : '';
    }
    
    const id = !!this.currentUser?.id ? this.currentUser.id : 0;
    if(this.role === 'CLIENT') {
      this.authService.getClient(id).subscribe({
        next: (response) => {
          this.client = response;
        },
        error: () => {
          
        }
      });
    } else {
      this.authService.getPetShelter(id).subscribe({
        next: (response) => {
          this.petShelter = response;
        },
        error: () => {
          
        }
      });
    }
  }

  takeImpression() {
    const commentText = this.impressionForm.get('comment')?.value;
    this.comment = {
      writtenTo: this.username,
      comment: !!commentText ? commentText : '',
      writtenBy: !!this.currentUser?.username ? this.currentUser.username : ''
    }
    this.userCommentService.createComment(this.comment as UserComment).subscribe(
      response => {
        this.router.navigate(['/profile', this.username]);
      },
      error => {

      }
    );

    const ratingNum = this.impressionForm.get('rating')?.value;
    this.rating = {
      rating: !!ratingNum ? ratingNum : 0,
      userRated: this.username,
      userRates: !!this.currentUser?.username ? this.currentUser.username : ''
    }
    this.userRatingService.createRating(this.rating as UserRating).subscribe(
      response => {
        this.router.navigate(['/profile', this.username]);
      },
      error => {

      }
    )
  }

  messageUser() {
    const messageText = this.messageForm.get('messageText')?.value;
    this.message = {
      text: !!messageText ? messageText : '',
      senderUsername: !!this.currentUser?.username ? this.currentUser.username : '',
      receiverUsername: this.username
    }
    this.messageService.sendMessage(this.message);
    this.router.navigate(['chat', this.username]);
  }

  activateImpressionForm() {
    if(this.impressionFormActivated) {
      this.impressionFormActivated = false;
    } else {
      this.impressionFormActivated = true;
    }
  }

  activateMessageForm() {
    if(this.messageFormActivated) {
      this.messageFormActivated = false;
    } else {
      this.messageFormActivated = true;
    }
  }
}
