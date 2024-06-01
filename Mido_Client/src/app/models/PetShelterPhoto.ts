import { PetShelter } from "./PetShelter";

export interface PetShelterPhoto {
    id?: number;
    photo: string;
    shelter: PetShelter;
}