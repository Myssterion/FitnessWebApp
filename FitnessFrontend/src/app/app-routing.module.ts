import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { ProgramComponent } from './program/program.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { CategoriesComponent } from './categories/categories.component';
import { MyprogramsComponent } from './myprograms/myprograms.component';
import { MyprogramComponent } from './myprogram/myprogram.component';
import { ProgramsComponent } from './programs/programs.component';
import { LoginComponent } from './login/login.component';
import { GuardService } from './service/guard.service';
import { DiaryComponent } from './diary/diary.component';
import { DiaryentryComponent } from './diaryentry/diaryentry.component';
import { GraphComponent } from './graph/graph.component';
import { InboxComponent } from './inbox/inbox.component';
import { CustomersupportComponent } from './customersupport/customersupport.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'programs', component: ProgramsComponent },
  {path: 'categories', component: CategoriesComponent},
  {path: 'user', component: UserComponent, canActivate: [GuardService]},
  {path: 'program', component: ProgramComponent},
  {path: 'login', component: LoginComponent},
  {path: 'diary', component: DiaryComponent, canActivate: [GuardService]},
  {path: 'diary/graphic', component: GraphComponent, canActivate: [GuardService]},
  {path: 'diary/add', component: DiaryentryComponent, canActivate: [GuardService]},
  {path: 'inbox', component: InboxComponent, canActivate: [GuardService]},
  {
    path: 'myprograms/:status',
    component: MyprogramsComponent, canActivate: [GuardService]
  },
  {
    path: 'myprograms', redirectTo: 'myprograms/active', pathMatch: "full",
  },
  { path: 'myprogram/:mode', component: MyprogramComponent, canActivate: [GuardService] },
  { path: 'customersupport', component: CustomersupportComponent, canActivate: [GuardService] },
  { path: 'user', component: UserComponent, canActivate: [GuardService] },
  {path: 'register', component: RegisterComponent},
  
  {path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,  { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
