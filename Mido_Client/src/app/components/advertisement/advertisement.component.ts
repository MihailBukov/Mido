import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Advertisement } from 'src/app/models/Advertisement';
import { AdvertisementComment } from 'src/app/models/AdvertisementComment';
import { Message } from 'src/app/models/Message';
import { AdvertisementCommentService } from 'src/app/services/advertisement-comment.service';
import { AdvertisementService } from 'src/app/services/advertisement.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.css']
})
export class AdvertisementComponent implements OnInit{
  ad!: Advertisement;
  comments: AdvertisementComment[] = [];
  comment!: AdvertisementComment;
  message!: Message;

  constructor(private adService: AdvertisementService, private adCommentsService: AdvertisementCommentService, 
    private messageService: MessageService, private route: ActivatedRoute, private fb: FormBuilder, private router: Router) {}

  commentForm = this.fb.group({
      comment: ['']
  });

  messageForm = this.fb.group({
    text: ['']
  });

  ngOnInit(): void {
    this.ad = this.route.snapshot.params['ad'];

    if(this.ad && this.ad.id) {
      this.adCommentsService.getAllAdComments(this.ad.id)
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
        console.error(error);
      }
    );
  }

  viewProfile(username: string) {
    this.router.navigate(['profile', username]);
  }

  messageUser(receiverUsername: string) {
    const messageText = this.messageForm.get('text')?.value;
    //here this.message should be set using messageText, sender and receiver username
    this.messageService.createMessage(this.message as Message).subscribe(
      response => {
        this.router.navigate(['chat', receiverUsername]);
      },
      error => {
        console.error(error);
      }
    );
  }

}
