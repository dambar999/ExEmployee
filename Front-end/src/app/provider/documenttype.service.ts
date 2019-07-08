import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class DocumenttypeService {

  constructor(private http: HttpClient) { }

  getAllDocumentType(): Observable<any> {
 return this.http.get('/ex-employee-portal/documenttype/get/docType');
  }

}
