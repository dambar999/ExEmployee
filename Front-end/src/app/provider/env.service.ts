import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class EnvService {
  public apiUrl = '';
  // Whether or not to enable debug mode
  public assetUrl = '/ex-employee/assets/';
  public enableDebug = true;
  constructor() {
  }
}
