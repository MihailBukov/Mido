import { Advertisement } from "./Advertisement";
import { User } from "./User";

export class AdvertisementComments {
    id?: number;
    ad: Advertisement;
    comment: string;
    writtenBy: User;
}