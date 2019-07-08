import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
// import {ForgotComponent } from './component/forgot/forgot.component';
import { componentRefresh } from '@angular/core/src/render3/instructions';
import { ProfileComponent } from './component/profile/profile.component';
import { ForgotpasswordComponent } from './component/forgotpassword/forgotpassword.component';
import { ChangepasswordComponent } from './component/changepassword/changepassword.component';
import { TicketComponent } from './component/ticket/ticket.component';
import { ListdocumentComponent } from './component/listdocument/listdocument.component';
import { ContactsComponent } from './component/contacts/contacts.component';
import { ListticketComponent } from './component/listticket/listticket.component';
import { DiscussComponent } from './component/discuss/discuss.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'forgotpassword', component: ForgotpasswordComponent },
  { path: 'changepassword', component: ChangepasswordComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '', component: LoginComponent },
  { path: 'ticket', component: TicketComponent },
  { path: 'document', component: ListdocumentComponent },
  { path: 'contacts', component: ContactsComponent },
  { path: 'listticket/:toaster', component: ListticketComponent },
  { path: 'listticket', component: ListticketComponent },
  { path: 'discuss/:id', component: DiscussComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
