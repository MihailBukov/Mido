import { Advertisement } from "./Advertisement";
import { User } from "./User";

export interface AdvertisementComments {
    id?: number;
    ad: Advertisement;
    comment: string;
    writtenBy: User;
}