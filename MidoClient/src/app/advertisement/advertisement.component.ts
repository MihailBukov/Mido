import { Component } from '@angular/core';

@Component({
  selector: 'app-advertisement',
  standalone: true,
  imports: [],
  templateUrl: './advertisement.component.html',
  styleUrl: './advertisement.component.css'
})
export class AdvertisementComponent {
  id?: number;
  title: string;
  timeOfCreation: Date;
  description: string;
  dogName: string;
  dogAge: number;
  dogKg: number;
  dogBreed: string;
  dogGender: string;
  dogColor: string;

  constructor(title: string, timeOfCreation: Date, description: string, dogName: string, dogAge: number, dogKg: number, dogBreed: string,
    dogGender: string, dogColor: string, id?: number) {
    this.id = id;
    this.title = title;
    this.timeOfCreation = timeOfCreation;
    this.description = description;
    this.dogName = dogName;
    this.dogAge = dogAge;
    this.dogKg = dogKg;
    this.dogBreed = dogBreed;
    this.dogGender = dogGender;
    this.dogColor = dogColor;
  }

}
