import { Role } from "./Role";
import { Status } from "./Status";

export interface User {
    id?: number;
    username: string;
    role: Role;
    status: Status;
}
