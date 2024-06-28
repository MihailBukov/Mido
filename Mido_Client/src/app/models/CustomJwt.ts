import { JwtPayload } from "jwt-decode";

export interface CustomJwtPayload {
  role?: string;
  id?: string;
}
