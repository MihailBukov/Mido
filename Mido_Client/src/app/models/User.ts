import { Status } from "./Status";

export interface User {
    id?: number;
    username: string;
    password: string;
    email: string;
    role: string;
    status: Status;
}