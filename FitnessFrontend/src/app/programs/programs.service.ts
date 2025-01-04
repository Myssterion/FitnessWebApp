import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ProgramInfo } from '../model/programInfo.model';
import { DomSanitizer } from '@angular/platform-browser';
import { Filter } from '../model/filter.model';

@Injectable({
  providedIn: 'root'
})
export class ProgramsService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  public getPrograms() : Observable<ProgramInfo[]>{
    return this.http.get<ProgramInfo[]>(`${this.apiServerUrl}/program`);
  }

  public getFilteredPrograms(args : Filter) : Observable<ProgramInfo[]>{
    return this.http.post<ProgramInfo[]>(`${this.apiServerUrl}/program/filter`, args);
  }

  getPictureById(pictureId: number): Observable<Blob> {
    return this.http.get(`${this.apiServerUrl}/picture/find/${pictureId}`, { responseType: 'blob' });
  }
}
