import { User } from "./User";

export interface Client {
    id?: number;
    firstName: string;
    middleName: string;
    lastName: string;
    age: number;
    country: string;
    city: string;
    photo: number;
    description: string;
    user: User;
}