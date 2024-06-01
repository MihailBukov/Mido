import { User } from "./User";

export interface UserRating {
    id?: number;
    rating: number;
    userRates: User;
    userRated: User;
}