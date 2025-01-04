import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RegisterService } from './register.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Register } from '../model/register.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {


  constructor(private registerService: RegisterService, private router: Router) {}

  public user: Register = new Register();
  avatar: string = "";
  usernameCheckMessage: string = "";
  isUsernameAvailable: boolean = true;

  checkUsernameAvailability() {
    if (this.user.username) {
      this.registerService.checkIsUsernameAvailable(this.user.username).subscribe(
        (response: boolean) => {
          if (response === false) {
            this.usernameCheckMessage = 'Username is already taken';
            this.isUsernameAvailable = false;
          } 
          else {
            this.usernameCheckMessage = '';
            this.isUsernameAvailable = true;
          }
        },
        (error: HttpErrorResponse) => {
          console.error('Error checking username availability:', error);
          this.usernameCheckMessage = 'Error checking username availability';
        }
      );
    } else {
      this.usernameCheckMessage = ''; 
      this.isUsernameAvailable = true;
    }
  }

  onRegisterUser(registerForm: NgForm) {
    var dataForm = new FormData();
    this.fillDataForm(dataForm,this.user);

     this.registerService.register(dataForm).subscribe(
      (response: HttpResponse<any>) => {
        if(response.status === 200) {
          registerForm.reset();
          alert("Registered successfully!");
          this.router.navigate(['/home']);

        }
        else{
          alert("Registered failed!");
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if(file){
      this.user.avatar = file;
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.avatar = e.target.result;
      }
      reader.readAsDataURL(file);
    }
  }

  removePreview() {
   this.avatar = "";
  }

  private fillDataForm(dataForm: FormData, user: Register){
    dataForm.append("name",user.name);
    dataForm.append("surname",user.surname);
    dataForm.append("username",user.username);
    dataForm.append("password",user.password);
    dataForm.append("city",user.city);
    dataForm.append("email",user.email);
    if(user.avatar !== null)
      dataForm.append("avatar",user.avatar,user.avatar.name);
  }
}
