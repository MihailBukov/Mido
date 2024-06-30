import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { CommentService } from 'src/app/services/comment.service';
import { RatingService } from 'src/app/services/rating.service';
import { Client } from 'src/app/models/Client';
import { PetShelter } from 'src/app/models/PetShelter';
import { User } from 'src/app/models/User';
import { UserComment } from 'src/app/models/UserComment';
import { UserRating } from 'src/app/models/UserRating';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: User | null;
  username: string = '';
  role!: string;
  client!: Client;
  petShelter: PetShelter;
  comment: UserComment = {} as UserComment;
  rating: UserRating = {} as UserRating;
  impressionFormActivated: boolean = false;
  impressionForm: FormGroup;
  comments: UserComment[] = [];
  ratings: UserRating[] = [];
  avarageRating: number;
  editMode: boolean = false;
  profileForm: FormGroup;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private userCommentService: CommentService,
    private userRatingService: RatingService,
  ) {
    this.impressionForm = this.fb.group({
      comment: [''],
      rating: [0]
    });
  }

  ngOnInit(): void {
    this.currentUser = this.authService.currentUserValue;
    this.username = this.route.snapshot.params['username'];
    this.role = this.route.snapshot.params['role'];

    this.profileForm = this.fb.group({
      firstName: [''],
      middleName: [''],
      lastName: [''],
      age: [''],
      country: [''],
      city: [''],
      description: [''],
      name: [''],
      capacity: [''],
      address: ['']
    });

    if (this.role === 'CLIENT') {
      this.authService.getClient(this.username).subscribe({
        next: (response) => {
          this.client = response;
          this.profileForm.patchValue(response);
        },
        error: () => {

        }
      });
    } else {
      this.authService.getPetShelter(this.username).subscribe({
        next: (response) => {
          this.petShelter = response;
          this.profileForm.patchValue(response);
        },
        error: () => {

        }
      });
    }

    this.userCommentService.getAllComments(this.username).subscribe({
      next: (response: UserComment[]) => {
        this.comments = response;
      },
      error: () => {

      }
    });

    this.userRatingService.getAllRatings(this.username).subscribe({
      next: (response: UserRating[]) => {
        this.ratings = response;
        this.avarageRating = this.ratings
          .map(rating => rating.rating)
          .reduce((acc, rating) => acc + rating, 0) / this.ratings.length;
      },
      error: () => {

      }
    });
  }

  takeImpression() {
    const impression = { ...this.impressionForm.value };

    this.comment = {
      writtenTo: this.username,
      comment: impression.comment || '',
      writtenBy: this.currentUser?.username || ''
    };

    this.userCommentService.createComment(this.comment).subscribe({
      next: (response) => {
        this.router.navigate(['/profile', this.username]);
      },
      error: (err) => {
        console.error('Error creating comment:', err);
      }
    });

    this.rating = {
      rating: impression.rating || 0,
      userRated: this.username,
      userRates: this.currentUser?.username || ''
    };

    this.userRatingService.createRating(this.rating).subscribe({
      next: () => {
        this.router.navigate(['/profile', this.username]);
      },
      error: (err) => {
        console.error('Error creating rating:', err);
      }
    });
  }

  activateImpressionForm() {
    this.impressionFormActivated = !this.impressionFormActivated;
  }

  toggleEditMode() {
    this.editMode = !this.editMode;
  }

  saveProfile() {
    if (this.role === 'CLIENT') {
      this.authService.updateClient(this.username, this.profileForm.value).subscribe({
        next: (response) => {
          this.client = response;
          this.toggleEditMode();
        },
        error: (err) => {
          console.error('Error updating client:', err);
        }
      });
    } else {
      this.authService.updatePetShelter(this.username, this.profileForm.value).subscribe({
        next: (response) => {
          this.petShelter = response;
          this.toggleEditMode();
        },
        error: (err) => {
          console.error('Error updating pet shelter:', err);
        }
      });
    }
  }
}
