import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Ticket } from '../model/ticket';
import { Ticketdetails } from '../model/ticketdetails';

@Injectable({
  providedIn: 'root'
})
export class ListticketserviceService {

  constructor(private http: HttpClient) { }

  showTickets(emp_id: any): Observable<any> {
    return this.http.get('/ex-employee-portal/ticket/getall?id=' + emp_id);
  }

  closeTicket(ticket: Ticketdetails): Observable<any> {
    return this.http.post('/ex-employee-portal/ticket/close', ticket);
  }

  openTicket(ticket: Ticketdetails): Observable<any> {
    return this.http.post('/ex-employee-portal/ticket/reopen', ticket);
  }

  showTicketsSupport(sid: any): Observable<any> {
    return this.http.get('/ex-employee-portal/ticket/get/supp?sid=' + sid);
  }

}
