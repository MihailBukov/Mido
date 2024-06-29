import { User } from "./User";

export interface UserComment {
    id?: number;
    writtenTo: string;
    comment: string;
    writtenBy: string;
}