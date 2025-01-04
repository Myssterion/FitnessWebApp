import { Component, OnInit } from '@angular/core';
import { ActivityLogEntry } from '../model/activitylogentry.model';
import { Exercise } from '../model/exercise.model';
import { Intensity } from '../model/intensity.model';
import { HttpErrorResponse } from '@angular/common/http';
import { DiaryentryService } from './diaryentry.service';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-diaryentry',
  templateUrl: './diaryentry.component.html',
  styleUrl: './diaryentry.component.css'
})
export class DiaryentryComponent implements OnInit{

  public diaryEntry : ActivityLogEntry = new ActivityLogEntry();
  public exercises : Exercise[] = [];
  public intensities : Intensity[] = [];

  constructor(private diaryEntryService: DiaryentryService, private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
  this.loadValues();
  }

  loadValues() {
    this.diaryEntryService.getExercises().subscribe(
      (response: Exercise[]) => {
        this.exercises = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )

    this.diaryEntryService.getIntensities().subscribe(
      (response: Intensity[]) => {
        this.intensities = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

onSubmit() {
  this.diaryEntry.date = new Date();
  this.diaryEntryService.addDiaryEntry(this.authService.getUserId(),this.diaryEntry).subscribe(
    (response: ActivityLogEntry) => {
      this.router.navigate(['/diary']);
    },
    (error : HttpErrorResponse) => {
      alert(error.message);
    }
  )
}

}
