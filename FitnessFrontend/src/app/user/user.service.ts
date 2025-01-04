import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
export class UserService{

    private apiServerUrl = environment.apiServerUrl;

    constructor(private http: HttpClient) { }
    
    public getUserById(userId: number): Observable<User>
    {
      return this.http.get<User>(`${this.apiServerUrl}/regularUser/find/${userId}`);
    }

    public updateUser(userId: number | null, user: User): Observable<User>
    {
      return this.http.put<User>(`${this.apiServerUrl}/regularUser/update/${userId}`,user);
    }

    public updateAvatar(userId: number, avatar: FormData) : Observable<HttpResponse<any>> {
      return this.http.post<HttpResponse<any>>(`${this.apiServerUrl}/avatar/update/${userId}`, avatar, { observe: 'response' });
    }
  
    public getAvatarById(userId: number) :Observable<Blob> {
      return this.http.get(`${this.apiServerUrl}/avatar/find/${userId}`, { responseType: 'blob' });
    }
}