import { Component } from '@angular/core';

@Component({
  selector: 'app-pet-shelter',
  standalone: true,
  imports: [],
  templateUrl: './pet-shelter.component.html',
  styleUrl: './pet-shelter.component.css'
})
export class PetShelterComponent {
  id?: number;
  name: string;
  country: string;
  city: string;
  capacity: number;
  address: string;
  description: string;
  photo: number;
  isVerified: boolean;

  constructor(name: string, country: string, city: string, capacity: number,
    address: string, description: string, photo: number, isVerified:boolean, id?: number) {
      this.id = id;
      this.name = name;
      this.country = country;
      this.city = city;
      this.capacity = capacity;
      this.address = address;
      this.description = description;
      this.photo = photo;
      this.isVerified = isVerified;
    }

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
