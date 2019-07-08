import { ElementRef, ViewChild, AfterViewChecked, Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GetempService } from 'src/app/provider/getemp.service';
import { Discuss } from 'src/app/model/discuss';
import { DiscussService } from 'src/app/provider/discuss.service';
import { SupportService } from 'src/app/provider/support.service';

import { Renderer2, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-discuss',
  templateUrl: './discuss.component.html',
  styleUrls: ['./discuss.component.css',
    '../../styles/bootstrap.css',
    '../../styles/buttons.css',
    '../../styles/styles.css',
  ]
})
export class DiscussComponent implements OnInit, AfterViewChecked {
  @ViewChild('scrollMe') private myScrollContainer: ElementRef;
  messages = [
    {
      'text': 'how are u?',
      'self': false
    }
  ];

  discussion: any[] = [];
  empName: any;
  email: any;
  emp_id: any;
  msg: Discuss;
  msgString: string;

  replyMessage = '';

  ticketId: number = null;
  sub: any;
  empSet: any;
  suppSet: any;
  empMap: any;
  suppMap: any;
  checkhr: any;
  dateTime: any;
  lastId: any;

  constructor(private activeroute: ActivatedRoute,
    private discussService: DiscussService,
    private router: Router,
    private getempService: GetempService,
    private supportService: SupportService,
    private renderer2: Renderer2,
    @Inject(DOCUMENT) private _document) {
      // document.getElementById('ticket').scrollTo = ;
    this.empSet = new Set();
    this.suppSet = new Set();
    this.empMap = new Map();
    this.suppMap = new Map();
    this.msg = new Discuss();
    this.checkhr = localStorage.getItem('checkhr');
  }

  ngOnInit() {
    // this.scrollToBottom();
    if (localStorage.getItem('loggedIn') !== ('true')) {
      this.router.navigateByUrl('/login');
    }
    this.empName = localStorage.getItem('name');
    this.email = localStorage.getItem('emp_id');
    if (this.checkhr === '1') {
      this.getempService.getSupportId(this.email).subscribe((response) => {
        if (response) {
          this.emp_id = response;
        }
      });
    } else {
      this.getempService.getEmployee(this.email).subscribe((response) => {
        if (response) {
          this.emp_id = response.id;
        }
      });
    }
  }

   openDiscuss(tid: any) {
     console.log('from open discuss');
    this.refreshMsgs();
    this.ticketId = tid;

    this.discussService.getAllDiscuss(this.ticketId).subscribe((response) => {
      if (response) {
        this.discussion = response;
        console.log(this.discussion);
        this.updateSets();
      }
    });
  }

  updateSets() {
    this.discussion.forEach(element => {
      if (element.employeeId === 0) {
        this.suppSet.add(element.supportId);
      } else {
        this.empSet.add(element.employeeId);
      }
    });

    this.empSet.forEach(element => {
      this.getempService.getEmpName(element).subscribe((response) => {
        this.empMap.set(element, response);
      });
    });
    this.suppSet.forEach(element => {
      this.supportService.getSuppNameFromId(element).subscribe((response) => {
        this.suppMap.set(element, response);
      });
    });
  }

  sendMsg() {
    this.msg.ticketId = this.ticketId;
    this.msg.message = this.msgString;
   this.msg.date = new Date();
    if (this.checkhr === '1') {
      this.msg.supportId = this.emp_id;
      this.discussService.sendSuppMsg(this.msg).subscribe((response) => {
        if (response) {
          this.msgString = '';
        }
        this.refreshMsgs();
      });
      console.log(this.msg.supportId);
    } else {
      this.msg.employeeId = this.emp_id;
      this.discussService.sendEmpMsg(this.msg).subscribe((response) => {
        if (response) {
          this.msgString = '';
        }
        this.refreshMsgs();
      });
    }
  }

  refreshMsgs() {
    console.log('from refresh msg');
    if (this.discussion.length !== 0) {
      this.lastId = this.discussion[this.discussion.length - 1].discussId;
    } else {
      this.lastId = 0;
    }
    console.log(this.lastId);
    this.discussService.getDiscussMoreThanId(this.lastId).subscribe((response) => {
      console.log(response);
      for ( let i = 0 ; i < response.length ; i++) {
        this.discussion.push(response[i]);
      }
      this.updateSets();
      // response.forEach(element => {
      //   this.discussion.push(element);
      //   // this.updateSets();
      // });
    });
  }
  ngAfterViewChecked() {
    // console.log('4545');
    // this.scrollToBottom();
}
scrollToBottom(): void {
  try {
      this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
  } catch (err) { }
}

}
