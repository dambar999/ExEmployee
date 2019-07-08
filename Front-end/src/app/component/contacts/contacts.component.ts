import { Component, OnInit } from '@angular/core';
import { SessionService } from 'src/app/provider/session.service';
import { Router } from '@angular/router';
import { EnvService } from '../../provider/env.service';
@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css',
    '../../styles/bootstrap.css',
    '../../styles/buttons.css',
    '../../styles/styles.css']
})
export class ContactsComponent implements OnInit {
  empName: String;
  documentPic: String;
  contactsPic: String;
  raiseTicketPic: String;
  listTicketPic: String;
  profilePic: String;
  logoutPic: String;
  loginPic: String;
  accoliteLogoPic: String;
  checkhr : String;

  constructor(private sessionService: SessionService, private router: Router, private envService: EnvService) { }

  ngOnInit() {
    this.documentPic = this.envService.assetUrl + 'img/my-document.png';
    this.contactsPic = this.envService.assetUrl + 'img/contact.png';
    this.raiseTicketPic = this.envService.assetUrl + 'img/riseticket.png';
    this.listTicketPic = this.envService.assetUrl + 'img/listticket.png';
    this.profilePic = this.envService.assetUrl + 'img/my-profile.png';
    this.logoutPic = this.envService.assetUrl + 'img/logout.png';
    this.loginPic = this.envService.assetUrl + 'img/login.svg';
    this.accoliteLogoPic = this.envService.assetUrl + 'img/acco-logo.png';
    if (localStorage.getItem('loggedIn') !== ('true')) {
      this.router.navigateByUrl('/login');

    }
    this.checkhr = localStorage.getItem('checkhr');
    this.empName = localStorage.getItem('name');
  }

  logout() {
    this.sessionService.logout();
    this.router.navigateByUrl('/login');
  }



}
