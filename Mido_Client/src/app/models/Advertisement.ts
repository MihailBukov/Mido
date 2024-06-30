import {Role} from "./Role";

export interface Advertisement {
    id?: number;
    title: string;
    timeOfCreation?: Date;
    description: string;
    dogName: string;
    dogAge: number;
    dogKg: number;
    dogBreed: string;
    dogGender: string;
    dogColor: string;
    country: string;
    city: string;
    createdBy: string;
    photo?: string;
    createdByRole: Role;
}
