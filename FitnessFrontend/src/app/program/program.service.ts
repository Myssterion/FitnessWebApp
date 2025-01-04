import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Program } from '../model/program.model';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { Comment } from '../model/comment.model';
import { Message } from '../model/message.model';
import { Payment } from '../model/payment.model';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {
  private apiServerUrl = environment.apiServerUrl;

  constructor(private http: HttpClient) { }

  getProgramById(id: number) : Observable<Program> {
    return this.http.get<Program>(`${this.apiServerUrl}/program/find/${id}`);
  }

  getCommentsById(id: number) : Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.apiServerUrl}/comment/find/${id}`);
  }

  postComment( comment: Comment) :Observable<Comment> {
    return this.http.post<Comment>(`${this.apiServerUrl}/comment/add`, comment);
  }

  sendMessage(msg: Message) : Observable<Message>{
    return this.http.post<Message>(`${this.apiServerUrl}/message/send`, msg);
  }

  joinProgram(payment: Payment) :Observable<Payment> {
    return this.http.post<Payment>(`${this.apiServerUrl}/regularUserProgram/subscribe`, payment);
  }

  getIsUserSubscribed(userId: number, programId: number) : Observable<Boolean> {
    return this.http.get<Boolean>(`${this.apiServerUrl}/regularUserProgram/find/isSubscribed/${userId}/${programId}`);
  }
}
