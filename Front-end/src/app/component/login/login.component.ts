import { Component, OnInit, NgZone, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/app/model/login';
import { LoginService } from 'src/app/provider/login.service';
import { SessionService } from 'src/app/provider/session.service';
import { Glogin } from 'src/app/model/glogin';
import { EnvService } from '../../provider/env.service';
declare const gapi: any;
import { ToasterService } from 'src/app/provider/toaster.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css',
    '../../styles/bootstrap.css',
    '../../styles/buttons.css',
    '../../styles/styles.css']
})
export class LoginComponent implements OnInit, AfterViewInit {

  auth2: any;
  login: Login;
  gLogin: Glogin;
  email: String;
  message: string;
  message1: string;
  message2: string;
  message3: string;
  faceBookLogoPic: String;
  linkedInLogoPic: String;
  twitterLogoPic: String;
  accoliteLogoPic: String;
  jgvjhkn

  constructor(private loginService: LoginService, private sessionService: SessionService, private envService: EnvService,
    private ngZone: NgZone, private router: Router, private toasterService: ToasterService
  ) { }

  ngOnInit() {
    this.twitterLogoPic = this.envService.assetUrl + 'img/twitter.png';
    this.linkedInLogoPic = this.envService.assetUrl + 'img/linkedin-icon.ico';
    this.faceBookLogoPic = this.envService.assetUrl + 'img/facebook-logo.ico';
    this.accoliteLogoPic = this.envService.assetUrl + 'img/acco-logo.png';
    this.login = new Login();
    this.gLogin = new Glogin();
  }

  ngAfterViewInit() {
    gapi.load('auth2', () => {
      this.auth2 = gapi.auth2.init();
    });
  }

  /*
  validate and check password
  */
  checkpassword() {
    this.email = this.login.email;
    this.sessionService.setLoggedIn(this.email);
    localStorage.setItem('glogin', 'false');
    const re = /\S+@\S+\.\S+/;
    if (!this.login.email) {
      this.message = 'email is empty';
      localStorage.setItem('message', this.message);
      localStorage.setItem('resp', '-1');
      this.toasterService.Success('-1');
      this.message1 = '';
      this.message2 = '';
      this.message3 = '';

    } else if (!this.login.password) {
      this.message1 = 'password is empty';
      localStorage.setItem('message', this.message1);
      localStorage.setItem('resp', '-1');
      this.toasterService.Success('-1');
      this.message = '';

      this.message2 = '';
      this.message3 = '';

    } else if (!re.test(String(this.login.email))) {
      this.message = 'Invalid email';
      localStorage.setItem('message', this.message);
      localStorage.setItem('resp', '-1');
      this.toasterService.Success('-1');
      this.message1 = '';
      this.message2 = '';
      this.message3 = '';
    } else {
      this.loginService.checkPassword(this.login).subscribe((response) => {

        if (response) {
          if (response.requestStatus === 1) {
            localStorage.setItem('toaster', '1');
            localStorage.setItem('message', response.message);
            localStorage.setItem('resp', response.requestStatus);
            this.router.navigateByUrl('/profile');


          } else if (response.requestStatus === 0) {
            console.log('from backend ' + response.message);
            localStorage.setItem('message', response.message);
            localStorage.setItem('resp', response.requestStatus);
            const msg = localStorage.getItem('resp');
            this.toasterService.Success(msg);
            this.message3 = 'Invalid credentials';
            this.message = '';
            this.message1 = '';
            this.message2 = '';
          }
        }
      });

    }
  }

  googleLogin() {
    gapi.auth2.getAuthInstance().signIn(
      {
        prompt: 'select_account'
      }
    ).then((userData) => {
      // get logged in user email id
      this.gLogin.email = userData.w3.U3;
      this.sessionService.setLoggedIn(this.gLogin.email);
      localStorage.setItem('glogin', 'true');
      this.ngZone.run(() => {
        this.loginService.googleLogin(this.gLogin).subscribe((response) => {
          if (response) {
            localStorage.setItem('toaster', '1');
            localStorage.setItem('message', response.message);
            localStorage.setItem('resp', response.requestStatus);
            if (response.requestStatus === 1) {
              this.loginService.checkhrLogin(this.gLogin).subscribe((Response) => {
                if (Response) {
                  if (Response.requestStatus === 1) {
                    // localStorage.setItem('message', Response.message );
                    // this.toasterService.Success(Response.requestStatus);
                    localStorage.setItem('checkhr', '1');
                    // localStorage.setItem('toaster', '1');
                    // localStorage.setItem('message', Response.message);
                    localStorage.setItem('resp', Response.requestStatus);
                    this.router.navigateByUrl('/listticket');
                  }
                }
              });
              this.router.navigateByUrl('/profile');
            } else if (response.requestStatus === 0) {
              this.message3 = 'Invalid credentials';
              this.message = '';
              this.message1 = '';
              this.message2 = '';
            }
          }
        });
      });
    }
    );
  }

  /*
  function to hide and show the password
  on click of fa fa-eye
  */
  myFunctionShowPass() {
    const x = document.getElementById('password');
    if (x.getAttribute('type') === 'text') {
      x.setAttribute('type', 'password');
    } else {
      x.setAttribute('type', 'text');
    }
  }
}
