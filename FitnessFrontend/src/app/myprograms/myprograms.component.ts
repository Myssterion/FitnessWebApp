import { Component, OnInit } from '@angular/core';
import { ProgramInfo } from '../model/programInfo.model';
import { ActivatedRoute, Router } from '@angular/router';
import { MyprogramsService } from './myprograms.service';
import { AuthService } from '../service/auth.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-myprograms',
  templateUrl: './myprograms.component.html',
  styleUrl: './myprograms.component.css'
})
export class MyprogramsComponent implements OnInit{

  status: string = "";
  programs: ProgramInfo[] = [];

  constructor(private router: Router, private route: ActivatedRoute,
     private myprogramsService: MyprogramsService, private authService: AuthService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.status = params['status'];
      if(this.status == "inactive")
        this.getInactivePrograms();
      else if(this.status == "active")
        this.getActivePrograms();
      else if(this.status == "subscribed")
        this.getSubscribedPrograms();
    });
  }
  getSubscribedPrograms() {
    this.myprogramsService.getSubscribedPrograms(this.authService.getUserId()).subscribe(
      (response: ProgramInfo[]) => {
        console.log(response);
        this.programs = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
     )
  }
  
  public getInactivePrograms() {
   this.myprogramsService.getInactivePrograms(this.authService.getUserId()).subscribe(
    (response: ProgramInfo[]) => {
      this.programs = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
   )
  }
  public getActivePrograms() {
    this.myprogramsService.getActivePrograms(this.authService.getUserId()).subscribe(
      (response: ProgramInfo[]) => {
        this.programs = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
     )
  }

  isActive(route: string): boolean {
    return this.router.url.startsWith(route);
  }

  deleteProgram(programId: number | null) {
    if(programId != null)
      this.myprogramsService.deleteProgram(programId).subscribe(
        (response: HttpResponse<200>) => {
        this.loadPrograms();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }

      )
  }

  deactivateProgram(programId: number|null) {
    if(programId != null) {
      this.myprogramsService.deactivateProgram(programId).subscribe(
        (response: HttpResponse<200>) => {
          this.loadPrograms();
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
          }
      )
    }
    }

  loadPrograms() {
    if(this.status === "active")
      this.getActivePrograms();
    else 
      this.getInactivePrograms();
  }
}
