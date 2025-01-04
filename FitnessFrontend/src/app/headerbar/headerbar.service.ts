import { Inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable, catchError, map, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category.model';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Program } from '../model/program.model';
import { DOCUMENT } from '@angular/common';


@Injectable({
  providedIn: 'root'
})
export class HeaderbarService {

  private apiServerUrl = environment.apiServerUrl;
  private avatar: string | null = null;
  private sessionStorage: Storage | null;

  constructor(private http: HttpClient, private sanitizer: DomSanitizer, @Inject(DOCUMENT) private document: Document) {

    this.sessionStorage = document.defaultView?.sessionStorage ?? null;
    this.sessionStorage?.removeItem("avatar")
    const temp = this.sessionStorage?.getItem("avatar");

    if (temp !== undefined && temp !== null) {
      this.avatar = JSON.parse(temp);
    }
  }

  public getCategoriesByName(searchTerm: any, limit: number): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiServerUrl}/category/seek?search=${encodeURIComponent(searchTerm)}&limit=${limit}`);
  }

  public getProgramsByName(searchTerm: any, limit: number): Observable<Program[]> {
    return this.http.get<Program[]>(`${this.apiServerUrl}/program/seek?search=${encodeURIComponent(searchTerm)}&limit=${limit}`);
  }

  public getAvatarById(userId: number): Observable<string> {
    if (this.sessionStorage) {
      const storedAvatar = this.sessionStorage.getItem('avatar');
      if (storedAvatar) {
        return of(storedAvatar);
      }
    }

    return this.http.get(`${this.apiServerUrl}/avatar/find/${userId}`, { responseType: 'blob' })
      .pipe(
        map((response: Blob) => {
          if (response && response.size > 0) {
          const avatarUrl = URL.createObjectURL(response);
          this.saveAvatarToLocalStorage(avatarUrl);
          return avatarUrl;
          } else {
            return "";
          }
        }),
        catchError(error => {
          console.error('Error fetching avatar', error);
          return of(''); 
        })
      );
  }

  private saveAvatarToLocalStorage(avatarUrl: string): void {
    if (this.sessionStorage) {
      this.sessionStorage.setItem("avatar", avatarUrl);
      this.avatar = avatarUrl;
    }
  }
}
