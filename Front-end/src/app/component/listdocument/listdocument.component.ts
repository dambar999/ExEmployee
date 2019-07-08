import { Component, OnInit, Input } from '@angular/core';
import { GetempService } from 'src/app/provider/getemp.service';
import { DocumentserviceService } from 'src/app/provider/documentservice.service';
import { SessionService } from 'src/app/provider/session.service';
import { ListticketserviceService } from 'src/app/provider/listticketservice.service';
import { Router } from '@angular/router';
import { TwoWords } from '../../twowords.pipe';
import { ToasterService } from 'src/app/provider/toaster.service';
import { EnvService } from '../../provider/env.service';

@Component({
  selector: 'app-listdocument',
  templateUrl: './listdocument.component.html',
  styleUrls: ['./listdocument.component.css',
    '../../styles/bootstrap.css',
    '../../styles/buttons.css',
    '../../styles/styles.css']
})
export class ListdocumentComponent implements OnInit {

  nothingToShow: number;
  showSpinner = true;
  jk: number = null;
  email: String;
  emp_id: number;
  doc: any[] = [];
  docSupport: any[] = [];
  empName: String;
  flag: any;
  b: any;
  map: any;
  discuss: any = [];
  mess: any = [];
  i: any;
  hideContact: String;
  hideProfile: String;
  hideTicket: String;
  tickets: any = [];
  checkhr: string;
  documentPic: String;
  contactsPic: String;
  raiseTicketPic: String;
  listTicketPic: String;
  profilePic: String;
  logoutPic: String;
  loginPic: String;
  accoliteLogoPic: String;

  messages = [
    {
      'text': '',
      'self': true
    }
  ];

  replyMessage = '';

  constructor(private getempService: GetempService, private documentService: DocumentserviceService,
    private sessionService: SessionService, private router: Router, private listTicketService: ListticketserviceService,
    private toasterService: ToasterService, private envService: EnvService) {
      this.nothingToShow = 0;
     }

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
    this.hideContact = '';
    this.hideProfile = '';
    this.hideTicket = '';

    this.empName = localStorage.getItem('name');
    this.email = localStorage.getItem('emp_id');
    this.checkhr = localStorage.getItem('checkhr');
    if (this.checkhr === '1') {
      this.hideContact = 'hidden';
      this.hideProfile = 'hidden';
      this.hideTicket = 'hidden';

    }
    if (this.checkhr === '1') {
      this.getempService.getSupportId(this.email).subscribe((response) => {
        if (response) {
          this.emp_id = response;
          this.listTicketService.showTicketsSupport(this.emp_id).subscribe((resp) => {
            this.tickets = resp;
            this.tickets.forEach(element => {
              // console.log('element is ' + element);
              this.documentService.showDocumentByTicket(element.ticketId).subscribe((r) => {
                this.showSpinner = false;
                for ( let i = 0 ; i < r.length ; i ++) {
                  this.doc.push(r[i]);
                }
                if ( this.doc.length === 0) {
                  this.nothingToShow = 1;
                  localStorage.setItem('message', 'nothing to show' );
                  this.toasterService.Success(-1);
                }
              });
            });
          });
        }
      });
    } else {
      this.getempService.getEmployee(this.email).subscribe((response) => {
        if (response) {
          this.emp_id = response.id;
          this.documentService.showDocument(this.emp_id).subscribe((resp) => {
            this.showSpinner = false;
            this.doc = resp;
            if ( this.doc.length === 0) {
              this.nothingToShow = 1;
              localStorage.setItem('message', 'nothing to show' );
              this.toasterService.Success(-1);
              console.log('nothing to show');
            }
            console.log(this.doc);
          });
        }
      });
    }
    this.map = new Map();

  }

  /*
  navigate to discuss page
  */
  func(i: any) {
    this.router.navigateByUrl('/discuss/' + i);
  }

  download(did: Number) {
    //console.log("Download " + did);
  }

  /*
  delete cookies
  */
  logout() {
    this.sessionService.logout();
    this.router.navigateByUrl('/login');
  }
}
