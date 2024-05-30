import { User } from "../User";

export class UserDto {
    id?: number;
    username: string;
    password: string;
    email: string;
    role: string;
  
    constructor(user: User) {
      this.id = user.id;
      this.username = user.username;
      this.password = user.password;
      this.email = user.email;
      this.role = user.role;
    }
}