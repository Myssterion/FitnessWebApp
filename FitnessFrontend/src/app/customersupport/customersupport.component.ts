import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Message } from '../model/message.model';
import { CustomersupportService } from './customersupport.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-customersupport',
  templateUrl: './customersupport.component.html',
  styleUrl: './customersupport.component.css'
})
export class CustomersupportComponent implements OnInit{

    message: string = "";
    conversation: Message[] = [];
    
    constructor(private authService: AuthService, private customerSupportService: CustomersupportService) {}

    ngOnInit(): void {
      this.getConversation();
    }

    getConversation() {
      this.customerSupportService.getConversation(this.getUserId()).subscribe(
        (response: Message[]) => {
          this.conversation = response;
        }
      ),
      (error: HttpErrorResponse) => {
        alert(error.message);
      }

    }

    sendMessage() {
      const msg = new Message(this.getUserId(),undefined,undefined,this.message,new Date().getTime());
      this.customerSupportService.sendMessage(msg).subscribe(
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
