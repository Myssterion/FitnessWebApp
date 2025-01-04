import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { User } from '../model/user.model';
import { FormGroup, NgForm } from '@angular/forms';
import { UserService } from './user.service';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit{

  avatar: any;
  newAvatar: File | null = null;
  avatarChanged: boolean = false;
  public user: User = new User();
  userForm : FormGroup = new FormGroup({});

  constructor(private userService: UserService, private authService: AuthService) { }

  ngOnInit(): void {
    this.getUser();
    this.getAvatar();
  }

  public getUser(): void {
    this.userService.getUserById(this.authService.getUserId()).subscribe(
      (response: User) => {
        this.user = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getAvatar(): void {
    this.userService.getAvatarById(this.authService.getUserId()).subscribe(
      (response: Blob) => {
        if (response !== null) 
        this.avatar = URL.createObjectURL(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onSaveUser(userForm: NgForm){
    console.log(userForm.value);
    this.userService.updateUser(this.user.id, userForm.value).subscribe(
       (response: User) =>
        {
          this.user = response;
          if(this.avatarChanged && this.newAvatar != null){
            this.avatarChanged = false;
            const formData = new FormData();
            formData.append('avatar', this.newAvatar, this.newAvatar.name);
            this.userService.updateAvatar(this.authService.getUserId(),formData).subscribe(
              (response:  HttpResponse<any>) => {
                if(response.status === 200)
                  this.getAvatar();
              },
              (error: HttpErrorResponse) => {
                alert(error.message);
              }
            )
          }
        },
        (error: HttpErrorResponse) =>
        {
          alert(error.message);
        }
    );
  }

    onFileSelected(event: any) {
      const file: File = event.target.files[0];
      if(file){
        this.avatarChanged = true;
        this.newAvatar = file;
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.avatar = e.target.result;
        }
        reader.readAsDataURL(file);
      }
    }
    
}
