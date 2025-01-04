import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Login } from '../model/login.model';
import { Observable, catchError, map, of } from 'rxjs';
import { DOCUMENT } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isLoggedin = false;
  private user: User | null = null;
  private apiServerUrl = environment.apiServerUrl;
  private localStorage: Storage | null;
  private sessionStorage: Storage | null;
  public notActivated: boolean = false;

  constructor(private router: Router, private http: HttpClient, @Inject(DOCUMENT) private document: Document) {
    this.localStorage = document.defaultView?.localStorage ?? null;
    this.sessionStorage = document.defaultView?.sessionStorage ?? null;

    const temp = this.localStorage?.getItem("user");

    if(temp !== undefined && temp !== null){
      this.user  = JSON.parse(temp);
      this.isLoggedin = true;
    }
  }

  login(login: Login): Observable<boolean> {
    return this.http.post<User>(`${this.apiServerUrl}/login`, login).pipe(
        map((response: User) => {
            this.user = response;
            if(this.localStorage !== null)
              this.localStorage.setItem('user', JSON.stringify(response));
            this.isLoggedin = true;
            return true;
        }),
        catchError((error: HttpErrorResponse) => {
          if(error.status === 403)
            this.notActivated = true;
            return of(false);
        })
    );
}

  logout(): void {
    this.user = null;
    if(this.localStorage !== null)  {
      this.localStorage.removeItem('user');
      this.sessionStorage?.removeItem('avatar');
    }
    this.isLoggedin = false;
    this.router.navigate(['/home']);
  }

  isLoggedIn(): boolean {
    return this.isLoggedin;
  }

  getUser() : User {
    if(this.user)
      return this.user;
    return new User();
  }
  getUserId() : number {
    if(this.isLoggedin && this.user !== null && this.user.id !== null){
      return this.user.id;
    }
    else
      return -1;
  }
}
