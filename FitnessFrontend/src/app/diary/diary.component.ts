import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ActivityLogEntry } from '../model/activitylogentry.model';
import { DiaryService } from './diary.service';
import { AuthService } from '../service/auth.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-diary',
  templateUrl: './diary.component.html',
  styleUrl: './diary.component.css'
})
export class DiaryComponent implements OnInit{

  public currentPage: number = 1;
  public pageSize: number = 10;
  public activitylog: ActivityLogEntry[] = [];


  constructor(private router: Router, private diaryService: DiaryService,  private authService: AuthService){}

ngOnInit(): void {
  this.loadActivityLogEntries();
}

  loadActivityLogEntries() {
    this.diaryService.getActivityLogEntries(this.authService.getUserId()).subscribe(
      (response: ActivityLogEntry[]) => {
        this.activitylog = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

isActive(route: string) {
  return this.router.url.startsWith(route);
}

downloadDiaryPdf() {
  this.diaryService.downloadDiaryAsPdf(this.authService.getUserId()).subscribe(
    (response: Blob) => {
    const blob = new Blob([response], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'diary.pdf'; 
    link.click();
    window.URL.revokeObjectURL(url); 
  });
  }
}
