import { Component } from '@angular/core';
import { PetShelterComponent } from '../pet-shelter/pet-shelter.component';

@Component({
  selector: 'app-pet-shelters',
  standalone: true,
  imports: [],
  templateUrl: './pet-shelters.component.html',
  styleUrl: './pet-shelters.component.css'
})
export class PetSheltersComponent {
  shelters: PetShelterComponent[];

  constructor(shelters: PetShelterComponent[]) {
    this.shelters = shelters;
  }
}
