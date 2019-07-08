import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private loggedInstatus = JSON.parse(localStorage.getItem('loggedIn') || 'false');

  constructor() { }

  setLoggedIn(email: String) {
    localStorage.setItem('loggedIn', 'true');
    localStorage.setItem('emp_id', email.toString());
  }

  get isLoggedIn() {
    return JSON.parse(localStorage.getItem('loggedIn') || this.loggedInstatus.toString());
  }

  logout() {
    localStorage.clear();
  }
}
