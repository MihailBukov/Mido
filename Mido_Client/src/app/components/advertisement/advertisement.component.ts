import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Advertisement } from 'src/app/models/Advertisement';
import { AdvertisementComment } from 'src/app/models/AdvertisementComment';
import { User } from 'src/app/models/User';
import { AdvertisementCommentService } from 'src/app/services/advertisement-comment.service';
import { AdvertisementService } from 'src/app/services/advertisement.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.css']
})
export class AdvertisementComponent implements OnInit {
  adId: number;
  currentUser: User | null;
  ad!: Advertisement;
  comments: AdvertisementComment[];
  comment!: AdvertisementComment;
  commentFormActivated: boolean = false;
  constructor(private adService: AdvertisementService, private adCommentsService: AdvertisementCommentService,
    private route: ActivatedRoute, private fb: FormBuilder, private router: Router,
    private authService: AuthService) {}

  commentForm = this.fb.group({
    comment: ['']
  });

  ngOnInit(): void {
    this.currentUser = this.authService.currentUserValue;
    this.adId = this.route.snapshot.params['id'];
    this.adService.getAd(this.adId).subscribe({
      next: (response: Advertisement) => {
        this.ad = response;
      },
      error: () => {
        // handle error
      }
    });

    this.adCommentsService.getAllAdComments(this.adId).subscribe({
      next: (data: AdvertisementComment[]) => {
        this.comments = data;
      },
      error: () => {
        console.error('Error fetching comments:');
      }
    });
  }

  createComment() {
    const commentText = this.commentForm.get('comment')?.value;
    this.comment = {
      advertisementId: this.adId,
      comment: !!commentText ? commentText : '',
      writtenBy: !!this.currentUser?.username ? this.currentUser.username : ''
    }
    this.adCommentsService.createAdComment(this.comment as AdvertisementComment).subscribe({
      next: () => {
        this.router.navigate(['/advertisement', this.adId]);
      },
      error: () => {
        
      }
    });
  }

  viewProfile(username: string) {
    this.router.navigate(['profile', username]);
  }

  activateCommentForm() {
    this.commentFormActivated = !this.commentFormActivated;
  }
}
