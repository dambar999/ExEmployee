import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ListticketserviceService } from 'src/app/provider/listticketservice.service';
import { GetempService } from 'src/app/provider/getemp.service';
import { Email } from 'src/app/model/email';
import { Ticket } from 'src/app/model/ticket';
import { SessionService } from 'src/app/provider/session.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToasterService } from 'src/app/provider/toaster.service';
import { Ticketdetails } from 'src/app/model/ticketdetails';
import { TwoWords } from '../../twowords.pipe';
import { TicketService } from 'src/app/provider/ticket.service';
import { Assignee } from 'src/app/model/assignee';
import { NgxSmartModalService  } from 'ngx-smart-modal';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { SummaryService } from 'src/app/provider/summary.service';
import { Observable } from 'rxjs';
import { EnvService } from '../../provider/env.service';
@Component({
  selector: 'app-listticket',
  templateUrl: './listticket.component.html',
  styleUrls: ['./listticket.component.css', '../../styles/bootstrap.css',
    '../../styles/buttons.css',
    '../../styles/styles.css']
})



export class ListticketComponent implements OnInit {


  @Input() toasterNumber: Number = null;
  private assigneeName: Assignee[];
  summary: String;
  nothingToShow:  number;
  showSpinner = true;
  temp: String;
  email: String;
  emp_id: number;
  tickets: any = [];
  chosenMod = '';
  empName: String;
  ticketStatus: String;
  ticketdetail: Ticketdetails;
  statusList: any = ['OPEN', 'CLOSED', 'REOPENED'];
  tempTicket: Ticket;
  tempString: String;
  hideContacts: string;
  hideProfile: String;
  hideTicket: String;
  p = 1;
  checkhr: String;
  assigne: String[];
  closerResult: string;
  tempNum: number;
  tickets2: any[];
  documentPic: String;
  contactsPic: String;
  raiseTicketPic: String;
  listTicketPic: String;
  profilePic: String;
  logoutPic: String;
  loginPic: String;
  accoliteLogoPic: String;
  listDropDownPic: String;

  constructor(private listTicketService: ListticketserviceService, private ticketService: TicketService,
    public ngxSmartModalService: NgxSmartModalService, private modalService: NgbModal,
    private getempService: GetempService,
    private sessionService: SessionService,
    private router: Router,
    private toasterService: ToasterService,
    private summaryService: SummaryService,
    private envService: EnvService
  ) {
    this.nothingToShow = 0;
    this.ticketStatus = this.statusList[0];
    this.tempTicket = new Ticket();
    this.tempNum = 0;
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
    this.listDropDownPic = this.envService.assetUrl + 'img/list-drop.png';
    const toast = localStorage.getItem('toaster');
    console.log('value of toaster is ' + toast);
    if (toast === '1') {
      localStorage.removeItem('toaster');
      const resp = localStorage.getItem('resp');
      this.toasterService.Success(resp);
      localStorage.removeItem('message');
    }
    if (toast === '2') {
      console.log('inside the function');
      localStorage.removeItem('toaster');
      const resp = localStorage.getItem('resp');
      console.log('resp is dekho ' + resp);
      this.toasterService.Success(resp);
      localStorage.removeItem('message');
    }
    // this.tempString = TwoWords:'ticket.summary';
    this.hideProfile = '';
    this.hideContacts = '';
    this.hideTicket = '';
    if (localStorage.getItem('hideButton') === '1') {
      localStorage.setItem('hideButton', '0');
    }
    if (localStorage.getItem('loggedIn') !== ('true')) {
      this.router.navigateByUrl('/login');
    }
    this.empName = localStorage.getItem('name');
    this.email = localStorage.getItem('emp_id');
    this.checkhr = localStorage.getItem('checkhr');
    if (this.checkhr === '1') {
      this.hideContacts = 'hidden';
      this.hideProfile = 'hidden';
      this.hideTicket = 'hidden';
      this.getempService.getSupportId(this.email).subscribe((response) => {
        if ( response) {
          this.emp_id = response;
          this.listTicketService.showTicketsSupport(this.emp_id).subscribe((resp) => {
            this.showSpinner = false;
            this.tickets = resp;
            this.tempTicket = this.tickets;
            if (this.tickets.length === 0) {
              this.nothingToShow = 1;
              localStorage.setItem('message', 'no item to show');
              this.toasterService.Success(-1);
           }
          });
        }
      });
    } else {
      this.hideContacts = '';
      this.hideProfile = '';
      this.getempService.getEmployee(this.email).subscribe((response) => {
        if (response) {
          this.emp_id = response.id;
          this.listTicketService.showTickets(this.emp_id).subscribe((resp) => {
            this.showSpinner = false;
            this.tickets = resp;
            this.tempTicket = this.tickets;
            console.log('lenght is 111 pp' + this.tickets.lenght);
            // this.tickets = this.tickets2;
            // console.log('7777');
            // this.t = resp;
             if (this.tickets.length === 0) {
               this.nothingToShow = 1;
               localStorage.setItem('message', 'nothing to show');
               this.toasterService.Success(-1);
            }
          });
        }
      });
    }

  }

  /*
  close the ticket
  */
  close1(ticket: Ticketdetails) {
    console.log(ticket);
    this.listTicketService.closeTicket(ticket).subscribe((response) => {
      localStorage.setItem('message', response.message);
      localStorage.setItem('resp', response.requestStatus);
      const choice = response.requestStatus;
      console.log(choice);
      this.toasterService.Success(choice);
    });
  }

  /*
  reopen the ticket
  */
  reopen(ticket: Ticketdetails) {
    this.listTicketService.openTicket(ticket).subscribe((response) => {
      localStorage.setItem('message', response.message);
      localStorage.setItem('resp', response.requestStatus);
      const choice = response.requestStatus;
      this.toasterService.Success(choice);
    });
  }

  getAssigne(ticket: Ticket) {
    this.ticketService.getSupportNameByticket(ticket.ticketId).subscribe((response) => {
    this.assigneeName = response;
    });
  }
  /*
  calls the reopen and close functions
  */
  modo(ticketStatus: any, ticket: Ticketdetails) {
    switch (ticketStatus) {
      case 2: {
        // this.ticketStatus = this.statusList[ticketId - 1];
        ticket.status = ticketStatus;
        // this.tempTicket.creatorId = ticket.creatorId;
        // this.tempTicket.summary = ticket.summary;
        // console.log(this.tempTicket);
        this.close1(ticket);
        break;
      }
      case 3: {
        // this.ticketStatus = this.statusList[ticketId - 1];
        ticket.status = ticketStatus;
         this.reopen(ticket);
        break;
      }
    }
  }

  updateTicket(ticketId: any) {
    localStorage.setItem('hideButton', '1');
    const gg = localStorage.getItem('hideButton');
    localStorage.setItem('ticketId', ticketId);
    this.router.navigateByUrl('/ticket');
  }

  /*
  destroy cookies
  */
  logout() {
    this.sessionService.logout();
    this.router.navigateByUrl('/login');
  }

  openmodel() {

  }
  openVerticallyCentered(content: any) {
    this.modalService.open(content, { centered: true });
  }

  openBuPopup(content: any) {
    this.modalService.open(content, { centered: true });
  }
  getSummary(ticketId: any) {
    // console.log('we are there id: ' + ticketId) ;
    this.summaryService.getSummary(ticketId).subscribe((Response) => {
      this.summary = Response;
    });

    // this.ticketService.getSummary(ticketId).subscribe((Response) => {
    //   // this.ticket.summary = Response.comments;
    //   this.summary = Response.comments;
    //   console.log('sfdsf ' + Response.comments);
    // });
  }

  uploadDocumentHr(ticketId: any) {
    localStorage.setItem('ticketId',ticketId);
    this.router.navigateByUrl('/ticket');
  }

  onoff() {
    console.log('value of tempNum is' + this.tempNum);
    if (this.tempNum === 0) {
      console.log('inside 0 wala');
          let j = -1;
          this.tickets2 = [];
          for (let i = 0; i < this.tickets.length ; i++) {
              if ( this.tickets[i].status === 1 || this.tickets[i].status === 3) {
                  j++;
                  this.tickets2[j] = this.tickets[i] ;
                }
            }
      this.tickets = this.tickets2;
    this.tempNum = 1;
    } else if ( this.tempNum === 1 ) {
      this.tickets = this.tempTicket;
      console.log('inside 1 wala');
      this.tempNum = 0;
    }
  }


}
