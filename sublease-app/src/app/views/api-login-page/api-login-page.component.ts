import { Component, ViewChild} from '@angular/core';  // must include ViewChild to prevent static view
import { AuthService } from '../../Services/Auth/auth.service';
import { LoginRequest } from '../../Models/LoginRequest/LoginRequest';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';    // must include to make forms work

@Component({
  selector: 'app-api-login',
  templateUrl: './api-login-page.component.html',
  styleUrls: ['./api-login-page.component.css']
})
export class ApiLoginPageComponent {

  constructor(private authService: AuthService, private router: Router) { }

  login(request: LoginRequest) {
    this.authService.apiLogin(request).subscribe(
      resp  => {
        console.log(resp);
        if (resp.registered) {
          sessionStorage.setItem('currentUser', resp.jwt);
          console.log(sessionStorage.getItem('currentUser'));
          this.router.navigate(['home']);
        }
      },
      err => console.log(err)
    );
  }

  loginWithGoogle() {
    this.router.navigate(['googleLogin']);
  }

  registerWithApi() {
    this.router.navigate(['apiRegister']);
  }
}

