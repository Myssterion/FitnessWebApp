import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  public checkIsUsernameAvailable(username: string) {
    return this.http.get<boolean>(`${this.apiServerUrl}/register/checkUsername/${username}`);
  }

  public register(registerInfo: FormData) : Observable<HttpResponse<any>>{
    return this.http.post<HttpResponse<any>>(`${this.apiServerUrl}/register`,registerInfo, { observe: 'response' });
  }
}
