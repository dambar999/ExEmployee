import { Injectable } from '@angular/core';
declare var toastr: any;

@Injectable({
  providedIn: 'root'
})
export class ToasterService {
  constructor() {
    toastr.options.preventDuplicates = true;
    toastr.options.positionClass = 'toast-top-center';
  }
  num: number;

  Success(choice: any) {
    const message = localStorage.getItem('message');
    if (choice == 1) {
      toastr.success(message);
    } else if (choice == 0) {
      toastr.error(message);
    } else if (choice == -1) {
      toastr.warning(message);
    }
    localStorage.removeItem('message');
    localStorage.removeItem('resp');
  }
}
