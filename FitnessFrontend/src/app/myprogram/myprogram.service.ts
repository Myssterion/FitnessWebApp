import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Program } from '../model/program.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyprogramService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getProgramById(id: number): Observable<Program> {
    return this.http.get<Program>(`${this.apiServerUrl}/program/find/${id}`);
  }

  addProgram(program: Program): Observable<Program> {
    return this.http.post<Program>(`${this.apiServerUrl}/program/add`, program);
  }

  updateProgram(id: number ,program: Program): Observable<Program> {
    return this.http.put<Program>(`${this.apiServerUrl}/program/update/${id}`, program);
  }

  uploadImagesForProgram(programPictures: FormData, programId: number): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>(`${this.apiServerUrl}/picture/upload/${programId}`, programPictures, { observe: 'response' });
  }
}
