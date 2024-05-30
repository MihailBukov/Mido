import { PetShelter } from "../PetShelter";

export class PetShelterDto {
    id?: number;
    name: string;
    country: string;
    city: string;
    capacity: number;
    address: string;
    description: string;
    photo: number;
    isVerified: boolean;
  
    constructor(shelter: PetShelter) {
        this.id = shelter.id;
        this.name = shelter.name;
        this.country = shelter.country;
        this.city = shelter.city;
        this.capacity = shelter.capacity;
        this.address = shelter.address;
        this.description = shelter.description;
        this.photo = shelter.photo;
        this.isVerified = shelter.isVerified;
      }
}