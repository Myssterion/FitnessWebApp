import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Attribute } from '../model/attribute.model';

@Injectable({
  providedIn: 'root'
})
export class AttributeService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getAttributes(categoryId: number) : Observable<Attribute[]> {
   return this.http.get<Attribute[]>(`${this.apiServerUrl}/attribute/find/${categoryId}`);
  }

}
