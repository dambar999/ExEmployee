import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SummaryService {

  constructor(private http: HttpClient) { }

  getSummary(ticketId): Observable<any> {
    return this.http.get('/ex-employee-portal/getSummary?ticketId=' + ticketId, {responseType: 'text'});
  }
}
