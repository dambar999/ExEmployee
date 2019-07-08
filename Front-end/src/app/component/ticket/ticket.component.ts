import { Component, OnInit, NgZone } from '@angular/core';
import { GetempService } from 'src/app/provider/getemp.service';
import { Ticket } from 'src/app/model/ticket';
import { TicketService } from 'src/app/provider/ticket.service';
import { Doc } from 'src/app/model/doc';
import { DocumentserviceService } from 'src/app/provider/documentservice.service';
import { SessionService } from 'src/app/provider/session.service';
import { Router } from '@angular/router';
import { ToasterService } from 'src/app/provider/toaster.service';
import { DocumenttypeService } from 'src/app/provider/documenttype.service';
import { RolesService } from 'src/app/provider/roles.service';
import { Roles } from 'src/app/model/roles';

// import { FileUploader } from 'ng2-file-upload';
import { FileSelectDirective, FileDropDirective, FileUploader } from 'ng2-file-upload/ng2-file-upload';
import { SupportService } from 'src/app/provider/support.service';
import { Support } from 'src/app/model/support';
import { TicketmappingService } from 'src/app/provider/ticketmapping.service';
import { Ticketmap } from 'src/app/model/ticketmap';
import { EnvService } from '../../provider/env.service';

const URL = 'http://localhost:8008/ex-employee-portal/document/upload';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css',
    '../../styles/bootstrap.css',
    '../../styles/buttons.css',
    '../../styles/styles.css']
})
export class TicketComponent implements OnInit {

  removeSelectSupport: [];
  supportSelected: Array<Support> = [];
  notSelectedSupport: Array<Support> = [];
  supportRemoveAndAdd: Support;
  hideButton: string;
  selectedOption: string;
  printedOption: string;
  documentType: DocumentType;
  email: String;
  emp_id: number;
  ticket: Ticket;
  selectedFile: Array<File> = [];
  prevSelectedFile: Array<File> = null;
  docName: String;
  document: Doc;
  currentUpload: File;
  i: number;
  ticketId: number;
  empName: String;
  message3: String;
  documentTypeId: number[] = [];
  departmentTypeId: number;
  departmentTypes: Roles[] = [];
  selectedDepartment: Roles;
  assignees: Support[] = [];
  selectedAssignee: Support;
  selectedAssignee1: [] = [];
  assigneeList: string[] = [];
  documentName: string[] = [];
  assigneeNamesList: any[] = [];
  supportIdList: number[] = [];
  ticketMap: Ticketmap;
  summary: string;
  hide: string;
  count: number;
  checkhr: String;
  readonlyTicketId: String;
  suppEmpId: number;
  suppDepartmentType: number;
  empIdForHrDocumentUpload: number;
  documentPic: String;
  contactsPic: String;
  raiseTicketPic: String;
  listTicketPic: String;
  profilePic: String;
  logoutPic: String;
  loginPic: String;
  accoliteLogoPic: String;

  // options = [
  //   { name: 'Form16', value: 'Form16'  },
  //   { name: 'Payslip', value: 'payslip' },
  //   { name: 'Resignation Letter', value: 'ResignationLetter'}
  // ];

  public uploader: FileUploader = new FileUploader({
    isHTML5: true
  });
  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };

  public hasBaseDropZoneOver:  false;

  constructor(private getempService: GetempService,
    private envService: EnvService,
    private ticketService: TicketService,
    private documentService: DocumentserviceService,
    private sessionService: SessionService,
    private router: Router,
    private toasterService: ToasterService,
    private documenttypeService: DocumenttypeService,
    private rolesService: RolesService,
    private supportService: SupportService,
    private ticketmappingService: TicketmappingService
  ) {
    // console.log(this.documentTypeId);
    // this.documentTypeId = 1;
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
    this.hide = '';
    this.checkhr = localStorage.getItem('checkhr');
    if (this.checkhr === '1') {
      // this.hide = 'hidden';
      this.readonlyTicketId = localStorage.getItem('ticketId');
      this.ticketService.getSummary(this.readonlyTicketId).subscribe((Response) => {
        this.summary = Response.comments;
        console.log('sfdsf ' + Response.comments);
      });
      this.ticketService.getEmpIdByTicketId(this.readonlyTicketId).subscribe((Response) =>{
        this.empIdForHrDocumentUpload = Response;
      });
      const suppEmail = localStorage.getItem('emp_id');
      this.supportService.getSuppDetailsFromEmail(suppEmail).subscribe((Response) => {
        this.suppEmpId = Response.employeeId;
        this.suppDepartmentType = Response.departmentType;
      });
    }


    this.hideButton = localStorage.getItem('hideButton');
    if (this.hideButton === '1') {
      const tid = localStorage.getItem('ticketId');
      this.ticketService.getSummary(tid).subscribe((Response) => {
        this.ticket.summary = Response.comments;
        console.log('sfdsf ' + Response.comments);
      });
    }
    if (localStorage.getItem('loggedIn') !== ('true')) {
      this.router.navigateByUrl('/login');
    }
    this.documenttypeService.getAllDocumentType().subscribe((response) => {
      if (response) {
        this.documentType = response;
      }

    });


    this.empName = localStorage.getItem('name');
    this.email = localStorage.getItem('emp_id');
    this.getempService.getEmployee(this.email).subscribe((response) => {
      if (response) {
        this.emp_id = response.id;
      }
    });

    this.rolesService.getDepartments().subscribe((response: Roles[]) => {
      if (response) {
        this.departmentTypes = response;
        console.log(this.departmentTypes);
      }
    });

    this.ticket = new Ticket();
    this.document = new Doc();
  }

  updateTicket(event) {
    this.ticket.ticketId = Number(localStorage.getItem('ticketId'));
    this.ticketService.updateTicket(this.ticket).subscribe((Response) => {
      localStorage.setItem('message', Response.message);
      localStorage.setItem('resp', Response.requestStatus);
      localStorage.setItem('hideButton', '0');
      const h = localStorage.getItem('hideButton');
      console.log(this.summary);
      localStorage.setItem('toaster', '2');
      this.router.navigateByUrl('/listticket');
      if (this.selectedFile.length ===  0) {
        console.log('no file');
        this.router.navigateByUrl('/listticket');
      } else {
        console.log('hihihihihhihihihihihihhi');
        console.log('file ' + this.selectedFile);
        this.ticketId = Number(localStorage.getItem('ticketId'));
        console.log('ticket id ' + this.ticketId);
        console.log('emp id ' + this.emp_id );
        console.log('doc type ' + this.documentTypeId);
        console.log('depart type ' + this.departmentTypeId);
        console.log('89899988989898989898898898');
      this.documentService.pushFileToStorage(this.selectedFile, this.ticketId,
         this.emp_id, this.documentTypeId, this.departmentTypeId).subscribe(response => {
        if (response.requestStatus === 1) {
          localStorage.setItem('toaster', '2');
          this.router.navigateByUrl('/listticket');
        } else {
          this.message3 = 'Failed ,Try Again!!';
        }
      });
    }

    });
  }

  /*
  add files to list
  */
  selectFile(event) {
    if (this.selectedFile.length > 0) {
      this.selectedFile = toArray(this.selectedFile).concat(toArray(event.target.files));

    } else {
      this.selectedFile = toArray(event.target.files);


    }
     this.selectedFile.forEach(() => this.documentTypeId.push(4));

  }

  /*
  send the ticket details and documents
  */
  send() {
    this.ticket.creatorId = this.emp_id;
    this.ticketService.createTicket(this.ticket).subscribe((response) => {
      if (response) {
        localStorage.setItem('toaster1', '1');
        localStorage.setItem('message', response.message);
        localStorage.setItem('resp', response.requestStatus);
        this.ticketId = response.requestStatus;

        this.ticketMap = new Ticketmap();
        this.ticketMap.exEmployeeId = this.emp_id;
        this.ticketMap.ticketId = this.ticketId;
        this.ticketMap.supportId = this.supportIdList;
        this.ticketMap.suppEmployeeId = this.assigneeList;
        this.ticketmappingService.addAssignee(this.ticketMap).subscribe((Response) => {
          if (Response.resp === 1) {
            localStorage.setItem('toaster', '2');
            this.router.navigateByUrl('/listticket');
            console.log('done');
          }
        });
      }

    }, () => {
    }, () => {
      if (this.selectedFile.length ===  0) {
        console.log('no file');
        this.router.navigateByUrl('/listticket');
      } else {
      this.documentService.pushFileToStorage(this.selectedFile, this.ticketId,
         this.emp_id, this.documentTypeId, this.departmentTypeId).subscribe(response => {
        if (response.requestStatus === 1) {
          localStorage.setItem('toaster', '2');
          this.router.navigateByUrl('/listticket');
        } else {
          this.message3 = 'Failed ,Try Again!!';
        }
      });
    }}
    );
  }

  /*
  destroy session and redirect to front end
  */
  logout() {
    this.sessionService.logout();
    this.router.navigateByUrl('/login');
  }

  changeDepartment() {
    this.departmentTypeId = this.selectedDepartment.roleId;
    this.supportService.getSupportEmpsByRole(this.departmentTypeId).subscribe((response: Support[]) => {
      this.assignees = response;
      for (let i = 0; i < this.assignees.length; i++) {
        this.assignees[i].roleId = this.departmentTypeId;
      }
      this.notSelectedSupport = [];
      this.count = 0;
      for ( let i = 0 ; i < this.assignees.length; i++) {
        let flag = 0;
        for ( let j = 0; j < this.supportSelected.length; j++ ) {
          if (this.assignees[i].employeeId === this.supportSelected[j].employeeId ) {
              flag = 1;
            }
        }
        if (flag === 0) {
          this.notSelectedSupport[this.count] = this.assignees[i];
          this.count++;
          flag = 0;
        }
      }
    });
      }

  addAssignee() {
     for ( let index = 0; index < this.supportSelected.length ; index++ ) {
    this.assigneeList.push(this.supportSelected[index].employeeId);
    // this.assigneeList.push(this.supportSelected[index].roleId);
    this.supportIdList.push(this.supportSelected[index].roleId);
    // this.assigneeNamesList.push(new Object({ empName: this.supportSelected[index].firstName + ' ' + this.supportSelected[index].lastName,
    //  empDept: this.selectedDepartment.roleName }));
     }
    // console.log(this.assigneeNamesList);
    localStorage.setItem('message', 'Assignee added');
    this.toasterService.Success(1);
  }

  addSelectedSupport( ) {
    for (let index = 0; index < this.selectedAssignee1.length; index++) {
      const element = this.selectedAssignee1[index];

      this.supportRemoveAndAdd = this.notSelectedSupport.filter(
        x => x.employeeId === element
      )[0];
      this.supportSelected.push(this.supportRemoveAndAdd);
      this.notSelectedSupport = this.notSelectedSupport.filter(
        x => x.employeeId !== this.supportRemoveAndAdd.employeeId
      );
    }
  }

  removeSelectedSupports() {
    for (let index = 0; index < this.removeSelectSupport.length; index++) {
      const element = this.removeSelectSupport[index];

      this.supportRemoveAndAdd = this.supportSelected.filter(
        x => x.employeeId === element
      )[0];
      this.notSelectedSupport.push(this.supportRemoveAndAdd);
      this.supportSelected = this.supportSelected.filter(
        x => x.employeeId !== this.supportRemoveAndAdd.employeeId
      );
    }
  }

  removeFile(i) {
    this.selectedFile.splice(i, 1);
  }

  refresh(): void {
    window.location.reload();
  }

  uploaddocumenthr(ticketId: any) {
    console.log('fefefeef');
    this.emp_id = this.suppEmpId;
    this.departmentTypeId = this.suppDepartmentType;
    this.ticketId = +this.readonlyTicketId;
    this.documentService.pushFileToStorage2(this.selectedFile, this.ticketId,
      this.emp_id, this.documentTypeId, this.departmentTypeId).subscribe(response => {
            if (response.requestStatus === 1) {

              localStorage.setItem('toaster', '1');
            localStorage.setItem('message', response.message);
            localStorage.setItem('resp', response.requestStatus);
               this.router.navigateByUrl('/listticket');
            } else {
              this.message3 = 'Failed ,Try Again!!';
            }
            console.log('inside function');
          });
}
}
function toArray(fileList) {
  return Array.prototype.slice.call(fileList);
}


