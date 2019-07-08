import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor(private http: HttpClient) { }

  createTicket(ticket: any): Observable<any> {
    return this.http.post('/ex-employee-portal/ticket/create', ticket);
  }

  getSummary(ticketId: any): Observable<any> {
    return this.http.get('/ex-employee-portal/ticket/summary?tid=' + ticketId);
  }
  updateTicket(ticket: any): Observable<any> {
    return this.http.post('/ex-employee-portal/ticket/updateTicket', ticket);
  }
  getSupportNameByticket(ticketId: any): Observable<any> {
    return this.http.get('/ex-employee-portal/ticket/get/supp/name?tid=' + ticketId);
  }
  getEmpIdByTicketId(ticketId: any): Observable<any> {
    return this.http.get('/ex-employee-portal/ticket/get/empId?tid=' + ticketId);
  }
}
