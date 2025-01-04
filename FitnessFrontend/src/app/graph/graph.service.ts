import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { Exercise } from '../model/exercise.model';

@Injectable({
  providedIn: 'root'
})
export class GraphService {

  private apiServerUrl = environment.apiServerUrl;
  
  constructor(private http: HttpClient) { }

  getExercises(userId : number) : Observable<Exercise[]> {
    return this.http.get<Exercise[]>(`${this.apiServerUrl}/activityLog/find/exercise/${userId}`);
  }

  getGraphData(userId: number, exerciseId: number, selectedOption: string, year: number, month: number) : Observable<{ [key: string]: number }> {
    let params = new HttpParams().set('exerciseId',exerciseId);
    params = params.set('period', selectedOption);

    if (selectedOption === 'year') {
      params = params.set('year', year);
    }

    if (selectedOption === 'month') {
      params = params.set('year', year);
      params = params.set('month', month);
    }

    return this.http.get<{ [key: string]: number }>(`${this.apiServerUrl}/activityLog/find/data/${userId}`, {params});
  }
}
