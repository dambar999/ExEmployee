import { Component } from '@angular/core';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';

import { NgModule } from '@angular/core';
import { NgxSmartModalModule, NgxSmartModalService } from 'ngx-smart-modal';




@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular';
  constructor(public ngxSmartModalService: NgxSmartModalService) {
  }
  }

