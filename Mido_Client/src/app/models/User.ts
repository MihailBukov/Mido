import { Role } from "./Role";
import { Status } from "./Status";
import {JwtPayload} from "jwt-decode";

export interface User extends JwtPayload {
    id?: number;
    username?: string;
    role?: Role;
    email?: string;
    password?: string;
}
