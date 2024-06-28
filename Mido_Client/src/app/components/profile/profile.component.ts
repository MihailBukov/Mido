import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '@progress/kendo-angular-notification';
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
  username: string = '';
  role!: string;
  client!: Client;
  petShelter!: PetShelter;
  comment!: UserComment;
  rating!: UserRating;
  message!: Message;

  constructor(private authService: AuthService, private route: ActivatedRoute, private router: Router, private fb: FormBuilder,
    private userCommentService: CommentService, private userRatingService: RatingService, private messageService: MessageService,
    private notificationService: NotificationService) {}

  impressionForm = this.fb.group({
      comment: [''],
      rating: 0
  });

  messageForm = this.fb.group({
    messageText: ['']
  });

  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];

    this.authService.getUserByUsername(this.username).subscribe(
      (response: User) => {
        this.role = !!response.role ? response.role.toString() : '';
      },
      error => {
        console.error(error);
      }
    );

    if(this.role === 'CLIENT') {
      //here use client service to set the client
    } else {
      //here use pet shelter service to set the pet shelter
    }
  }

  takeImpression() {
    const commentText = this.impressionForm.get('comment')?.value;
    //this.comment now should be set using the commentText, the advertisement id and the current user id
    this.userCommentService.createComment(this.comment as UserComment).subscribe(
      response => {
        this.notificationService.show({
          content: 'Comment has been created',
          type: { style: 'success', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
        this.router.navigate(['']); //should navigate you back to the same profile. Purpose is to refresh.
      },
      error => {
        this.notificationService.show({
          content: 'There was an error while leaving the comment.',
          type: { style: 'success', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
      }
    );

    const ratingNum = this.impressionForm.get('rating')?.value;
    //this.rating should be set using ratingNum and the users
    this.userRatingService.createRating(this.rating as UserRating).subscribe(
      response => {
        this.notificationService.show({
          content: 'Rating has been created',
          type: { style: 'success', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
        this.router.navigate(['profile']); //should navigate you back to the same profile. Purpose is to refresh.
      },
      error => {
        this.notificationService.show({
          content: 'There was an error while leaving a rating.',
          type: { style: 'error', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
      }
    )
  }

  messageUser() {
    const messageText = this.messageForm.get('messageText')?.value;
    //this.message should be set here
    this.messageService.sendMessage(this.message);
    this.router.navigate(['chat', this.username]);// here username represents the receiver in the new chat
  }
}
