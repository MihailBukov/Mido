import { User } from "./User";

export interface UserRating {
    id?: number;
    rating: number;
    userRates: string;
    userRatesUsername?: string;
    userRated: string;
}