import { User } from "./User";

export class UserComment {
    id?: number;
    writtenTo: User;
    comment: string;
    writtenBy: User;
}