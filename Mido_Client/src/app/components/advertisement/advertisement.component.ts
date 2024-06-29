import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Advertisement } from 'src/app/models/Advertisement';
import { AdvertisementComment } from 'src/app/models/AdvertisementComment';
import { Message } from 'src/app/models/Message';
import { User } from 'src/app/models/User';
import { AdvertisementCommentService } from 'src/app/services/advertisement-comment.service';
import { AdvertisementService } from 'src/app/services/advertisement.service';
import { AuthService } from 'src/app/services/auth.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.css']
})
export class AdvertisementComponent implements OnInit{
  adId: number;
  currentUser: User | null;
  ad!: Advertisement;
  comments: AdvertisementComment[] = [];
  comment!: AdvertisementComment;
  message!: Message;
  commentFormActivated: boolean = false;
  messageFormActivated: boolean = false;

  constructor(private adService: AdvertisementService, private adCommentsService: AdvertisementCommentService, 
    private messageService: MessageService, private route: ActivatedRoute, private fb: FormBuilder, private router: Router,
    private authService: AuthService) {}

  commentForm = this.fb.group({
      comment: ['']
  });

  messageForm = this.fb.group({
    text: ['']
  });

  ngOnInit(): void {
    this.currentUser = this.authService.currentUserValue;
    this.adId = this.route.snapshot.params['id'];
    this.adService.getAd(this.adId).subscribe({
      next: (response: Advertisement) => {
        this.ad = response;
      }, 
      error: () => {

      }
    });

    
    if(this.ad && this.adId) {
      this.adCommentsService.getAllAdComments(this.adId)
      .subscribe(
        (data: AdvertisementComment[]) => {
          this.comments = data;
        },
        error => {
          console.error('Error fetching comments:', error);
        }
      );
    }
  }

  createComment() {
    const commentText = this.commentForm.get('comment')?.value;
    //this.comment now should be set using the commentText, the advertisement id and the current user id
    this.adCommentsService.createAdComment(this.comment as AdvertisementComment).subscribe(
      response => {
        this.router.navigate(['advertisement', this.ad?.id]);
      }, 
      error => {

      }
    );
  }

  viewProfile(username: string) {
    this.router.navigate(['profile', username]);
  }

  messageUser(receiverUsername: string) {
    const messageText = this.messageForm.get('text')?.value;
    //here this.message should be set using messageText, sender and receiver username
    this.messageService.sendMessage(this.message);
    this.router.navigate(['chat', receiverUsername]);// here username represents the receiver in the new chat
  }

  activateCommentForm() {
    if(this.commentFormActivated) {
      this.commentFormActivated = false;
    } else {
      this.commentFormActivated = true;
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
