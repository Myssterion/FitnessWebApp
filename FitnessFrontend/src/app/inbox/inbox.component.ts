import { Component, OnInit } from '@angular/core';
import { InboxService } from './inbox.service';
import { AuthService } from '../service/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Message } from '../model/message.model';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrl: './inbox.component.css'
})
export class InboxComponent implements OnInit {

  message: string = "";
  messages: Message[] = [];
  participantId: number = 0;
  conversation: Message[] = [];

  constructor(private inboxService: InboxService, private authService: AuthService) {

  }

  ngOnInit(): void {
   this.loadInbox();
  }

  loadInbox() {
    this.inboxService.getMessages(this.authService.getUserId()).subscribe(
      (response: Message[]) => {
        this.messages = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  showConversation(receiverId: number | null) {
    if(receiverId != null) {
      this.participantId = receiverId;
      console.log(this.authService.getUserId() + "  " + this.participantId);
    this.inboxService.getConversation(this.authService.getUserId(), this.participantId).subscribe(
      (response: Message[]) => {
        this.conversation = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
  }

  sendMessage() {
    const msg = new Message(this.authService.getUserId(),this.participantId,undefined,this.message,new Date().getTime());
    console.log(msg);
    this.inboxService.sendMessage(msg).subscribe(
      (response: Message) => {
       this.conversation.unshift(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
    }

    getUserId() : number{
     return this.authService.getUserId();
    }
}
