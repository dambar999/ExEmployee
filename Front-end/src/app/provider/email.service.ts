import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(private http: HttpClient) { }

  sendMail(email: any): Observable<any> {

    return this.http.post('/ex-employee-portal/sendSimpleEmail', email);

  }
}
