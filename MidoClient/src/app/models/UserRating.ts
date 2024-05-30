import { User } from "./User";

export class UserRating {
    id?: number;
    rating: number;
    userRates: User;
    userRated: User;
}