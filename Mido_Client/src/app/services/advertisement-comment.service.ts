import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdvertisementComment } from '../models/AdvertisementComment';

@Injectable({
  providedIn: 'root'
})
export class AdvertisementCommentService {

  private baseUrl = 'http://localhost:8080/api/advertisementComment';

  constructor(private http: HttpClient) { }

  createAdComment(adComment: AdvertisementComment): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}`, adComment);
  }

  editAdComment(id: number, adComment: AdvertisementComment): Observable<AdvertisementComment> {
    return this.http.patch<AdvertisementComment>(`${this.baseUrl}/${id}`, adComment);
  }

  deleteAdComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getAdComment(id: number): Observable<AdvertisementComment> {
    return this.http.get<AdvertisementComment>(`${this.baseUrl}/${id}`);
  }

  getAllAdComments(id: number): Observable<AdvertisementComment[]> {
    return this.http.get<AdvertisementComment[]>(`${this.baseUrl}/comments/${id}`)
  }

}
