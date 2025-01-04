import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Message } from '../model/message.model';


@Injectable({
  providedIn: 'root'
})
export class CustomersupportService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getConversation(userId: number) : Observable<Message[]>{
    return this.http.get<Message[]>(`${this.apiServerUrl}/message/conversation/customersupport/${userId}`);
  }

  sendMessage(msg: Message) : Observable<Message>{
    return this.http.post<Message>(`${this.apiServerUrl}/message/send/customersupport`, msg);
  }
}
