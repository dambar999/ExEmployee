import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Employee } from '../model/employee';

@Injectable({
  providedIn: 'root'
})
export class GetempService {

  constructor(private http: HttpClient) { }

  getEmployee(email: String): Observable<any> {

    return this.http.get('/ex-employee-portal/details?email=' + email);
  }

  getSupportId(email: String): Observable<any> {
    return this.http.get('/ex-employee-portal/supp/details?email=' + email);
  }

  getEmpName(id: number): Observable<any> {
    return this.http.get('/ex-employee-portal/get/name?eid=' + id, { responseType: 'text' });
  }
}
