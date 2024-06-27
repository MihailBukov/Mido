import { Role } from "./Role";
import { Status } from "./Status";

export interface User {
    id?: number;
    username: string;
    password: string;
    email: string;
    role: Role;
    status: Status;
}