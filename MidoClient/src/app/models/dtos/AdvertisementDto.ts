import { Advertisement } from "../Advertisement";

export class AdvertisementDto{
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

    
    constructor(ad: Advertisement) {
        this.id = ad.id;
        this.title = ad.title;
        this.timeOfCreation = ad.timeOfCreation;
        this.description = ad.description;
        this.dogName = ad.dogName;
        this.dogAge = ad.dogAge;
        this.dogKg = ad.dogKg;
        this.dogBreed = ad.dogBreed;
        this.dogGender = ad.dogGender;
        this.dogColor = ad.dogColor;
    }
}