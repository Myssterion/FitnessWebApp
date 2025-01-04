import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Category } from '../model/category.model';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getCategories() : Observable<Category[]> {
   return this.http.get<Category[]>(`${this.apiServerUrl}/category/find`);
  }

  getUserSubscriptions(id: number) : Observable<Category[]>{
    return this.http.get<Category[]>(`${this.apiServerUrl}/regularUser/subscriptions/${id}`);
  }

  subscribeUser(userId: number, categoryId: number): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>(`${this.apiServerUrl}/regularUser/subscribe/${userId}/${categoryId}`, { observe: 'response' });
  }
  unsubscribeUser(userId: number, categoryId: number): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>(`${this.apiServerUrl}/regularUser/unsubscribe/${userId}/${categoryId}`, { observe: 'response' });
  }
}
