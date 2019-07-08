import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Email } from 'src/app/model/email';
import { EmailService } from 'src/app/provider/email.service';
import { EnvService } from '../../provider/env.service';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css',
    '../../styles/bootstrap.css',
    '../../styles/buttons.css',
    '../../styles/styles.css']
})
export class ForgotpasswordComponent implements OnInit {

  message: String;
  email: Email;
  accoliteLogoPic: String;
  constructor(private mailService: EmailService
    , private router: Router, private envService: EnvService) {

  }

  ngOnInit() {
    this.accoliteLogoPic = this.envService.assetUrl + 'img/acco-logo.png';
    this.email = new Email();
  }

  sendMail() {

    this.mailService.sendMail(this.email).subscribe((response) => {
      this.router.navigateByUrl('/login');
    });
    if (this.email.emailId == null) {
      this.message = 'Email field is empty';
    } else {
      this.message = 'Password sent to your Email';
    }
  }
}
