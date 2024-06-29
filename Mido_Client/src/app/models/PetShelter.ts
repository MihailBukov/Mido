import { User } from "./User";

export interface PetShelter extends User {
    name?: string;
    country?: string;
    city?: string;
    capacity?: number;
    address?: string;
    description?: string;
    photo: string;
    isVerified: boolean;
}
