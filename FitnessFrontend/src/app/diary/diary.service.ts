import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { ActivityLogEntry } from '../model/activitylogentry.model';

@Injectable({
  providedIn: 'root'
})
export class DiaryService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getActivityLogEntries(userId : number) : Observable<ActivityLogEntry[]> {
    return this.http.get<ActivityLogEntry[]>(`${this.apiServerUrl}/activityLog/find/${userId}`);
  }

  downloadDiaryAsPdf(userId: number) : Observable<Blob> {
    const headers = new HttpHeaders({
      'Accept': 'application/pdf', 
    });

    return this.http.get(`${this.apiServerUrl}/activityLog/download/${userId}`, {
      headers: headers,
      responseType: 'blob' 
    });
  }
}
