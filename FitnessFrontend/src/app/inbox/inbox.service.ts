import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Message } from '../model/message.model';

@Injectable({
  providedIn: 'root'
})
export class InboxService {

  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getMessages(userId: number) : Observable<Message[]> {
    return this.http.get<Message[]>(`${this.apiServerUrl}/message/inbox/${userId}`);
  }

  getConversation(userId: number, participantId: number) : Observable<Message[]>{
    return this.http.get<Message[]>(`${this.apiServerUrl}/message/inbox/${userId}/conversation/${participantId}`);
  }

  sendMessage(msg: Message) : Observable<Message>{
    return this.http.post<Message>(`${this.apiServerUrl}/message/send`, msg);
  }
}
