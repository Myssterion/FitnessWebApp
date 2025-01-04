import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { AppBootstrapModule } from './app-bootstrap/app-bootstrap.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { ProgramComponent } from './program/program.component';
import { provideRouter } from '@angular/router';
import { routes } from './app-routing.module';
import { RegisterComponent } from './register/register.component';
import { HeaderbarComponent } from './headerbar/headerbar.component';
import { CategoriesComponent } from './categories/categories.component';
import { MyprogramsComponent } from './myprograms/myprograms.component';
import { MyprogramComponent } from './myprogram/myprogram.component';
import { ProgramsComponent } from './programs/programs.component';
import { LoginComponent } from './login/login.component';
import { DiaryComponent } from './diary/diary.component';
import { DiaryentryComponent } from './diaryentry/diaryentry.component';
import { GraphComponent } from './graph/graph.component';
import { InboxComponent } from './inbox/inbox.component';
import { CustomersupportComponent } from './customersupport/customersupport.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    HomeComponent,
    ProgramComponent,
    RegisterComponent,
    HeaderbarComponent,
    CategoriesComponent,
    MyprogramsComponent,
    MyprogramComponent,
    ProgramsComponent,
    LoginComponent,
    DiaryComponent,
    DiaryentryComponent,
    GraphComponent,
    InboxComponent,
    CustomersupportComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppBootstrapModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule,
    NgbModule,
    NgbPaginationModule
  ],
  providers: [
    provideClientHydration(), 
    provideRouter(routes)
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
