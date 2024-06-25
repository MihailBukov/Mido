import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Advertisement } from 'src/app/models/Advertisement';
import { AdvertisementComment } from 'src/app/models/AdvertisementComment';
import { AdvertisementCommentService } from 'src/app/services/advertisement-comment.service';
import { AdvertisementService } from 'src/app/services/advertisement.service';

@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.css']
})
export class AdvertisementComponent implements OnInit{
  ad?: Advertisement;
  comments: AdvertisementComment[] = [];

  constructor(private adService: AdvertisementService, private adCommentsService: AdvertisementCommentService, 
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    const adId = this.route.snapshot.params['id'];

    this.adCommentsService.getAllAdComments(adId)
      .subscribe(
        (data: AdvertisementComment[]) => {
          this.comments = data;
        },
        error => {
          console.error('Error fetching comments:', error);
        }
      );

    this.adService.getAd(adId).subscribe(
      (data: Advertisement) => {
        this.ad = data;
      },
      error => {
        console.error(error);
      }
    )
  }

  createComment() {

  }

  viewProfile() {

  }

  messageUser() {
    
  }

}
