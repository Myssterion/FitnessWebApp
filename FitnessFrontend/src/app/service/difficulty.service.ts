import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Difficulty } from '../model/difficulty.model';


@Injectable({
  providedIn: 'root'
})
export class DifficultyService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getDifficulties() : Observable<Difficulty[]> {
   return this.http.get<Difficulty[]>(`${this.apiServerUrl}/difficulty`);
  }
}
