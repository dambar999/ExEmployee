import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Doc } from '../model/doc';
import { Discuss } from '../model/discuss';


@Injectable({
  providedIn: 'root'
})
export class DocumentserviceService {

  constructor(private http: HttpClient) { }

  pushFileToStorage(file: Array<File>, ticketId: any, emp_id: any, documentTypeId: Array<number>, departmentTypeId: any): Observable<any> {

    const formData: any = new FormData();
    for (let index = 0; index < file.length; index++) {
      // console.log("tick"+document.ticketId)
      formData.append('files', file[index]);
      // console.log("abc " + formData.get('files').length);
    }
    formData.append('id', ticketId);
    formData.append('emp', emp_id);
    for (let index = 0; index < documentTypeId.length; index++) {
      formData.append('documentTypeId', Number(documentTypeId[index]));
    }
    formData.append('departmentTypeId', departmentTypeId);
    // console.clear();
    console.log(formData.getAll('files'));
    console.log(typeof(formData.getAll('documentTypeId')[0]));
    // console.log("abc " + formData.get('files').length);
    // const req = new HttpRequest("POST", "/ex-employee-portal/upload", formData, {
    //   reportProgress : true,
    //   responseType : 'text'
    // });

    // return this.http.post("/ex-employee-portal/upload",formData);
    
    return this.http.post('/ex-employee-portal/document/upload', formData);
  }

  pushFileToStorage2(file: Array<File>, ticketId: any, emp_id: any, documentTypeId: Array<number>, departmentTypeId: any): Observable<any> {

    
    const formData: any = new FormData();
    for (let index = 0; index < file.length; index++) {
      formData.append('files', file[index]);
    }
    formData.append('id', ticketId);
    formData.append('emp', emp_id);
    for (let index = 0; index < documentTypeId.length; index++) {
      formData.append('documentTypeId', Number(documentTypeId[index]));
    }
    formData.append('departmentTypeId', departmentTypeId);
    // console.clear();
    console.log(formData.getAll('files'));
    console.log(typeof(formData.getAll('documentTypeId')[0]));
    // console.log("abc " + formData.get('files').length);
    // const req = new HttpRequest("POST", "/ex-employee-portal/upload", formData, {
    //   reportProgress : true,
    //   responseType : 'text'
    // });

    // return this.http.post("/ex-employee-portal/upload",formData);
    console.log('in fil estorage 2');
    return this.http.post('/ex-employee-portal/document/hr/upload', formData);
  }

  showDocument(emp_id: any): Observable<any> {
    return this.http.get('/ex-employee-portal/document/get/emp/all?id=' + emp_id);
  }
  showDocumentByTicket(ticketId: any): Observable<any> {
    return this.http.get('/ex-employee-portal/document/get/tkt/all?id=' + ticketId);
  }

  downloadDocument(doc_id: any): Observable<any> {
    return this.http.get('/ex-employee-portal/document/get/file?id=' + doc_id);
  }

  downloadLink(doc_id: any): Observable<HttpResponse<Blob>> {
    return this.http.get<Blob>('/ex-employee-portal/document/get/file?id=' + doc_id, {
      observe: 'response',
      responseType: 'blob' as 'json'
    });
  }

  getDiscussion(ticketId: any): Observable<any> {
    return this.http.get('/ex-employee-portal/discuss/getall?ticketId=' + ticketId);
  }

  sendMessage(discuss: Discuss): Observable<any> {
    return this.http.post('/ex-employee-portal/discuss/insert', discuss);
  }
}
