import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exercise } from '../model/exercise.model';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Intensity } from '../model/intensity.model';
import { ActivityLogEntry } from '../model/activitylogentry.model';

@Injectable({
  providedIn: 'root'
})
export class DiaryentryService {
  

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getExercises() : Observable<Exercise[]> {
    return this.http.get<Exercise[]>(`${this.apiServerUrl}/exercise`);
  }

  getIntensities() : Observable<Intensity[]>{
     return this.http.get<Intensity[]>(`${this.apiServerUrl}/intensity`);
  }

  addDiaryEntry(userId: number, diaryEntry: ActivityLogEntry) : Observable<ActivityLogEntry> {
    return this.http.post<ActivityLogEntry>(`${this.apiServerUrl}/activityLog/add/${userId}`, diaryEntry);
  }
}
