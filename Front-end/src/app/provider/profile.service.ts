import { Injectable } from '@angular/core';
import { HttpClient , HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }

  changePasswordService(login: any): Observable<any> {
    return this.http.post('/ex-employee-portal/update/password', login);

  }
  updateDetails(emp: any): Observable<any> {

    return this.http.post('/ex-employee-portal/update/details', emp);
  }
  uploadImage(image: File , email_id: any): Observable<any> {
    const pic = new FormData();
    pic.append('image', image);
    pic.append('email', email_id);
    // const formData = new FormData();

    // formData.append('image', image);

    return this.http.post('/ex-employee-portal/document/upload/profilepic', pic);
  }

  getProfilePic(email: any): Observable<any> {
    console.log('e i' + email);
    return this.http.post('/ex-employee-portal/document/getprofilepic', email, { responseType: 'blob' });
  }
}
