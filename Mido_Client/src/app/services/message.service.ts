import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private baseUrl = 'localhost:8080/api';

  constructor(private http: HttpClient) { }
}