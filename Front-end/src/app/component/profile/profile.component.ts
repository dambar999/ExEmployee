import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileService } from 'src/app/provider/profile.service';
import { Login } from 'src/app/model/login';
import { Employee } from 'src/app/model/employee';
import { GetempService } from 'src/app/provider/getemp.service';
import { SessionService } from 'src/app/provider/session.service';
import { Response } from 'selenium-webdriver/http';
import { ToasterService } from 'src/app/provider/toaster.service';
import { SupportService } from 'src/app/provider/support.service';
import { EnvService } from '../../provider/env.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css',
    '../../styles/bootstrap.css',
    '../../styles/buttons.css',
    '../../styles/styles.css']
})

export class ProfileComponent implements OnInit {

  login: Login;
  pass: String;
  confirmPass: String;
  resultText: string;
  empName: String;
  empEmail: string;
  contactNumber: String;
  empId: String;
  emp: any;
  emailChanged: Boolean;
  contactChanged: Boolean;
  message: string;
  message1: string;
  hidePassword: String;
  selectedPic: File;
  documentPic: String;
  contactsPic: String;
  raiseTicketPic: String;
  listTicketPic: String;
  profilePic: String;
  logoutPic: String;
  loginPic: String;
  accoliteLogoPic: String;
  checkhr : String;


  constructor(private router: Router,
    private profileService: ProfileService,
    private getempService: GetempService,
    private sessionService: SessionService,
    private toasterService: ToasterService,
    private supportService: SupportService,
    private envService: EnvService) {
    const toast = localStorage.getItem('toaster');
    if (toast === '1') {
      localStorage.removeItem('toaster');
      const resp = localStorage.getItem('resp');
      this.toasterService.Success(resp);
      localStorage.removeItem('message');
    }
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
    localStorage.setItem('hideButton', '0');

    if (localStorage.getItem('loggedIn') !== ('true')) {
      this.router.navigateByUrl('/login');
    }


    this.emailChanged = false;
    this.contactChanged = false;

    this.empEmail = localStorage.getItem('emp_id');
    if (localStorage.getItem('glogin') === 'true') {
      this.hidePassword = 'hidden';
    } else {
      this.hidePassword = '';
    }
    console.log(localStorage.getItem('glogin'));
    console.log(this.hidePassword);
    this.getempService.getEmployee(this.empEmail).subscribe((response) => {
      console.log(response);
      if (response) {
        this.empId = response.id;
        this.emp = response;

        this.login = new Login();
        this.login.email = localStorage.getItem('emp_id');
        this.empEmail = localStorage.getItem('emp_id');
        this.contactNumber = this.emp.phoneNumber;
        this.empName = this.emp.firstName;
        localStorage.setItem('name', this.empName.toString());
        this.empId = this.emp.employeeId;
      } else {
        this.empEmail = localStorage.getItem('emp_id');
        this.supportService.getSuppNameFromEmail(this.empEmail).subscribe((resp) => {
          if (resp) {
            localStorage.setItem('name', resp);
            console.log(resp);
          }
        });
      }
    });
    this.checkhr = localStorage.getItem('checkhr');
  }

  /*
  changes the password
  */
  changePassword() {
    this.resultText = '';
    const re = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8})');
    if (this.pass === this.confirmPass) {
      if (re.test(String(this.pass))) {
        this.login.password = this.pass;
        return this.profileService.changePasswordService(this.login).subscribe((response) => {
          localStorage.setItem('message', response.message);
          localStorage.setItem('resp', response.requestStatus);
          const resp = response.requestStatus;
          if (response.requestStatus === 1) {
            this.resultText = 'Successfully Changed the password';
            localStorage.setItem('message', this.resultText);
            localStorage.setItem('resp', '1');
            this.toasterService.Success('1');
          } else {
            this.resultText = 'Password Change was unsuccessfull try again later';
            localStorage.setItem('message', this.resultText);
            localStorage.setItem('resp', '0');
            this.toasterService.Success('0');
          }
        });
      } else {
        this.resultText = 'Password must contain min 8 characters ,1 lower case ,1 upper case and 1 special character';
        localStorage.setItem('message', this.resultText);
        localStorage.setItem('resp', '0');
        this.toasterService.Success('0');
      }
    } else {
      this.resultText = 'Both the fields do not match';
      localStorage.setItem('message', this.resultText);
      localStorage.setItem('resp', '0');
      this.toasterService.Success('0');
    }
  }

  /*
  changes the contact details
  */
  updateContact() {
    if (this.contactChanged === true) {
      this.message = '';
      const re = new RegExp('^[0-9]*$');

      if (!re.test(String(this.contactNumber))) {
        this.message = 'Not a number';
        localStorage.setItem('message', this.message1);
        localStorage.setItem('resp', '0');
        this.toasterService.Success('0');
      } else if (this.contactNumber.length < 10) {
        this.message = 'Phone number can\'t be less then 10 digits';
        localStorage.setItem('message', this.message);
        localStorage.setItem('resp', '0');
        this.toasterService.Success('0');
      } else if (this.contactNumber.length > 10) {
        this.message = 'Phone number can\'t be more than 10 digits';
        localStorage.setItem('message', this.message);
        localStorage.setItem('resp', '0');
        this.toasterService.Success('0');
      } else {
        this.message = '';
        this.emp.phoneNumber = this.contactNumber;
        this.profileService.updateDetails(this.emp).subscribe((response) => {
          if (response.requestStatus === 1) {
            this.message = 'Phone Number changed successfully';
            localStorage.setItem('message', this.message);
            localStorage.setItem('resp', '1');
            this.toasterService.Success('1');
          } else {
            this.message = 'Unsuccessful ';
            localStorage.setItem('message', this.message);
            localStorage.setItem('resp', '0');
            this.toasterService.Success('0');
          }
        });
      }

      this.contactChanged = false;
    }
  }

  /*
  changes the Email details
  */
  updateEmail() {
    if (this.emailChanged === true) {
      this.message1 = '';
      const re = /\S+@\S+\.\S+/;

      if (!re.test(String(this.empEmail))) {
        this.message1 = 'Invalid Email Id';
        localStorage.setItem('message', this.message1);
        localStorage.setItem('resp', '0');
        this.toasterService.Success('0');

      } else {
        this.emp.emailId = this.empEmail;
        this.profileService.updateDetails(this.emp).subscribe((response) => {
          if (response.requestStatus === 1) {
            localStorage.setItem('emp_id', this.emp.emailId);
            this.message1 = 'Email Id changed successfully';
            localStorage.setItem('message', this.message1);
            localStorage.setItem('resp', '1');
            this.toasterService.Success('1');
          } else {
            this.message1 = ' Unsuccessful ';
            localStorage.setItem('message', this.message1);
            localStorage.setItem('resp', '0');
            this.toasterService.Success('0');
          }
        });
      }

      this.emailChanged = false;
    }
  }

  changeEmail() {
    this.emailChanged = true;
  }

  changeContact() {
    this.contactChanged = true;
  }

  /*
  destroy cookies
  */
  logout() {
    this.sessionService.logout();
    this.router.navigateByUrl('/login');
  }

  // onFileUpload(event) {
  //   const file = event.target.files[0];
  //   const email_id = localStorage.getItem('emp_id');
  //   this.profileService.uploadImage(file , email_id).subscribe((response) => {
  //     localStorage.setItem('message', response.message);
  //     console.log('message is ' + response.message);
  //       // localStorage.setItem('resp', '0');
  //       this.toasterService.Success('1');
  //   });
  //   }
  //    getBase64Image(img) {
  //     const canvas = document.createElement('canvas');
  //     canvas.width = img.width;
  //     canvas.height = img.height;
  //     const ctx = canvas.getContext('2d');
  //     ctx.drawImage(img, 0, 0);
  //     const dataURL = canvas.toDataURL('image/png');
  //     return dataURL.replace(/^data:image\/(png|jpg);base64,/, '');
  // }
  }
