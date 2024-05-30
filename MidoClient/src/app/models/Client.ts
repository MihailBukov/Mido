export class Client {
    id?: number;
    firstName: string;
    middleName: string;
    lastName: string;
    age: number;
    country: string;
    city: string;
    photo: number;
    description: string;

    constructor(firstName: string, middleName: string, lastName: string, age: number, 
        country: string, city: string, photo: number, description: string, id?: number) {
          this.id = id;
          this.firstName = firstName;
          this.middleName = middleName;
          this.lastName = lastName;
          this.age = age;
          this.country = country;
          this.city = city;
          this.photo = photo;
          this.description = description;
        }
}