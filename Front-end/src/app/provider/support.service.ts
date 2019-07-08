import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SupportService {

  constructor(private http: HttpClient) { }

  getSupportEmpsByRole(rid: any): Observable<any> {
    return this.http.get('/ex-employee-portal/support/get/emps?rid=' + rid);
  }

  getSuppNameFromId(id: number): Observable<any> {
    return this.http.get('/ex-employee-portal/support/get/namei?sid=' + id, { responseType: 'text' });
  }

  getSuppNameFromEmail(email: string): Observable<any> {
    return this.http.get('/ex-employee-portal/support/get/namee?email=' + email, { responseType: 'text' });
  }
  getSuppDetailsFromEmail(email: string): Observable<any> {
    return this.http.get('/ex-employee-portal/support/get/details?email=' + email);
  }
}
