import { Component } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { Login } from '../model/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

 
  login: Login = new Login();
  loginError: boolean = false;
  activatedError: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
  this.authService.login(this.login).subscribe(
    success => {
      if(success) {
        this.router.navigate(['/home']);

      }
      else {
       if(this.authService.notActivated)
        this.activatedError = true;
      else
        this.loginError = true;

      }
    }
  ) 
}

}
