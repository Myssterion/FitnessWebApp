import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { ProgramInfo } from '../model/programInfo.model';

@Injectable({
  providedIn: 'root'
})
export class MyprogramsService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getActivePrograms(userId: number) : Observable<ProgramInfo[]> {
    return this.http.get<ProgramInfo[]>(`${this.apiServerUrl}/program/find/active/${userId}`);
  }
  getInactivePrograms(userId: number) : Observable<ProgramInfo[]> {
    return this.http.get<ProgramInfo[]>(`${this.apiServerUrl}/program/find/inactive/${userId}`);
  }

  getSubscribedPrograms(userId: number) : Observable<ProgramInfo[]> {
    return this.http.get<ProgramInfo[]>(`${this.apiServerUrl}/regularUserProgram/find/subscribed/${userId}`);
  }

  deleteProgram(id: number) : Observable<HttpResponse<any>> {
    return this.http.delete<HttpResponse<any>>(`${this.apiServerUrl}/program/delete/${id}`, { observe: 'response' })
  }

  deactivateProgram(id: number) : Observable<HttpResponse<any>> {
   return this.http.put<HttpResponse<any>>(`${this.apiServerUrl}/program/deactivate/${id}`, {observe: 'response'});
  }
}
