import { User } from "./User";

export interface UserRating {
    id?: number;
    rating: number;
    userRates: string;
    userRatesName?: string;
    userRated: string;
    userRatedName?: string;
}
