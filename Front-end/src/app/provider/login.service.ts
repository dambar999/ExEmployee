import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }


  checkPassword(login: any): Observable<any> {
    return this.http.post('/ex-employee-portal/login/user', login);
  }

  googleLogin(gLogin: any): Observable<any> {
    return this.http.post('/ex-employee-portal/login/google', gLogin);
  }

  checkhrLogin(gLogin: any): Observable<any> {
    return this.http.post('/ex-employee-portal/login/checkHrLogin',gLogin);
  }
}
