import { Component } from '@angular/core';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css'
})
export class ClientComponent {
  id?: number;
  firstName: string;
  middleName: string;
  lastName: string;
  age: number;
  country: string;
  city: string;
  photo: number;
  description: string;

  constructor(firstName: string, middleName: string, lastName: string, age: number, 
    country: string, city: string, photo: number, description: string, id?: number) {
      this.id = id;
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
      this.age = age;
      this.country = country;
      this.city = city;
      this.photo = photo;
      this.description = description;
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
