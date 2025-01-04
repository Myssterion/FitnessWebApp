import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { ProgramService } from './program.service';
import { Program } from '../model/program.model';
import { ActivatedRoute } from '@angular/router';
import { Comment } from '../model/comment.model';
import { AuthService } from '../service/auth.service';
import { Message } from '../model/message.model';
import { Payment } from '../model/payment.model';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-program',
  templateUrl: './program.component.html',
  styleUrl: './program.component.css'
})
export class ProgramComponent implements OnInit{

  videoUrl: SafeResourceUrl | undefined;
  public program: Program = new Program();
  public payment: Payment = new Payment();
  public comments: Comment[] = [];
  public comment: string = "";
  public message: string = "";
  public showAll: boolean = false;
  public isUserSubscribed = false;

  constructor(private programService: ProgramService, private route: ActivatedRoute,
    public authService: AuthService, private sanitizer: DomSanitizer){}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id']; 
      this.getProgram(id);
      this.getComments(id);
    });
  }

  getIsUserSubscribed() {
    if(this.program && this.program.id) {
   this.programService.getIsUserSubscribed(this.authService.getUserId(), this.program.id).subscribe(
    (response: Boolean) => { 
        this.isUserSubscribed = response.valueOf();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
   )
  }
  }

  getProgram(id: number) {
    this.programService.getProgramById(id).subscribe(
      (response: Program) => {
        this.program = response;
        this.getIsUserSubscribed();
        if(this.program.video)
          this.videoUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.program.video);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  getComments(id: number) {
    this.programService.getCommentsById(id).subscribe(
      (response: Comment[]) => {
        if(response !== null)
          this.comments = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  toggleShowAll() {
    this.showAll = !this.showAll;
  }

  joinProgram() { 
    if(this.program != null && this.program.id && this.payment){
      this.payment.userId = this.authService.getUserId();
      this.payment.programId = this.program.id;
      console.log(this.payment);
      this.programService.joinProgram(this.payment).subscribe(
        (response: Payment) => {
         console.log(response);
         this.isUserSubscribed = true;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      )
    }
  }

  postComment() {
    const newComment = new Comment(undefined, this.comment, this.program.id != null ? this.program.id : -1, this.authService.getUserId(), new Date().getTime());
    this.programService.postComment(newComment).subscribe(
    (response: Comment) => {
      this.comments.unshift(response);
      this.comment = "";
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
   )
  }

  sendMessage() {
    if(this.program != null && this.program.instructor != null && this.program.instructor.id){
    const msg = new Message(this.authService.getUserId(),this.program.instructor?.id,undefined,this.message,new Date().getTime());
    console.log(msg);
    this.programService.sendMessage(msg).subscribe(
      (response: Message) => {
       console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
    }

}
