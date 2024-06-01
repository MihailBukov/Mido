import { User } from "./User";

export interface Message {
    id?: number;
    message: string;
    sender: User;
    receiver: User;
}