import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  constructor(private http: HttpClient) { }

  getDepartments(): Observable<any> {
    return this.http.get('/ex-employee-portal/roles/get/depts');
  }
}