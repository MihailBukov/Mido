import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Advertisement } from '../models/Advertisement';

@Injectable({
  providedIn: 'root'
})
export class AdvertisementService {

  private baseUrl = 'http://localhost:8080/api/advertisement';

  constructor(private http: HttpClient) { }

  searchAds(country: string, city: string, breed: string,
    age: string, gender: string): Observable<Advertisement[]> {
    return this.http.get<Advertisement[]>(`${this.baseUrl}?country=${country}&city=${city}&breed=${breed}&age=${age}&gender=${gender}`);
  }

  createAd(ad: Advertisement): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}`, ad, {
      headers: {
        'Access-Control-Allow-Origin': '*'
      },
      withCredentials: true
    });
  }

  editAd(id: number, ad: Advertisement): Observable<Advertisement> {
    return this.http.patch<Advertisement>(`${this.baseUrl}/${id}`, ad);
  }

  getAd(id: number): Observable<Advertisement> {
    return this.http.get<Advertisement>(`${this.baseUrl}/${id}`)
  }

  deleteAd(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`)
  }
}
