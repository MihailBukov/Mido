import { User } from "./User";

export interface Client extends User {
    firstName?: string;
    middleName?: string;
    lastName?: string;
    age?: number;
    country?: string;
    city?: string;
    photo: string;
    description?: string;
}
