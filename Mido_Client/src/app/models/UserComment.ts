import { User } from "./User";

export interface UserComment {
    id?: number;
    writtenTo: User;
    comment: string;
    writtenBy: User;
}