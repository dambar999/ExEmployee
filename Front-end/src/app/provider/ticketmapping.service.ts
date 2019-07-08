import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketmappingService {

  constructor(private http: HttpClient) { }

  addAssignee(ticketMap: any): Observable<any> {
    return this.http.post('/ex-employee-portal/ticketmapping/add', ticketMap);
  }
}
