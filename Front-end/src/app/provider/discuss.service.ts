import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscussService {

  constructor(private http: HttpClient) { }

  getAllDiscuss(ticketId: any): Observable<any> {
    return this.http.get('/ex-employee-portal/discuss/getall?ticketId=' + ticketId);
  }

  sendEmpMsg(msg: any): Observable<any> {
    console.log('employee 6666');
    return this.http.post('/ex-employee-portal/discuss/insert/employee', msg);
  }

  sendSuppMsg(msg: any): Observable<any> {
    console.log('support 55555');
    return this.http.post('/ex-employee-portal/discuss/insert/support', msg);
  }

  getDiscussMoreThanId(id: any): Observable<any> {
    return this.http.get('/ex-employee-portal/discuss/get/empList?id=' + id);
  }
}
