import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { ProfileComponent } from './component/profile/profile.component';
import { ForgotpasswordComponent } from './component/forgotpassword/forgotpassword.component';
import { ChangepasswordComponent } from './component/changepassword/changepassword.component';
import { FormsModule } from '@angular/forms';
import { TicketComponent } from './component/ticket/ticket.component';
import { ListdocumentComponent } from './component/listdocument/listdocument.component';
import { ContactsComponent } from './component/contacts/contacts.component';
import { ListticketComponent } from './component/listticket/listticket.component';
import { DiscussComponent } from './component/discuss/discuss.component';
import { ToasterService } from './provider/toaster.service';
import { TwoWords } from './twowords.pipe';
import { AngularFileUploaderModule } from 'angular-file-uploader';
import {FileUploadModule} from 'ng2-file-upload';
import {NgxPaginationModule} from 'ngx-pagination';
import {Event} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { EnvServiceProvider } from './provider/env.service.provider';

import { NgxSmartModalModule } from 'ngx-smart-modal';
import { SpinnerComponent } from './component/spinner/spinner.component';
import { FilterPipe } from './pipe/filter.pipe';



@NgModule({
  declarations: [
    AppComponent,
    FilterPipe,
    LoginComponent,
    ForgotpasswordComponent,
    ProfileComponent,
    ChangepasswordComponent,
    TicketComponent,
    ListdocumentComponent,
    ContactsComponent,
    ListticketComponent,
    DiscussComponent,
    TwoWords,
    SpinnerComponent
  ],
  imports: [
    NgbModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AngularFileUploaderModule,
    FileUploadModule,
    NgxPaginationModule,
    NgxSmartModalModule.forRoot()
    // DropdownModule
  ],
  providers: [ToasterService, TwoWords, EnvServiceProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
