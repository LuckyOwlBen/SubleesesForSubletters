import { Component, OnInit, ViewChild } from '@angular/core';  // must include ViewChild to prevent static view
import { AuthService } from '../../Services/Auth/auth.service';
import { Router } from '@angular/router';
import { GoogleAuthRequest } from '../../Models/GoogleAuthRequest/GoogleAuthRequest';
import { LoginRequest } from '../../Models/LoginRequest/LoginRequest';
import { NgForm } from '@angular/forms';    // must include to make forms work

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(private as: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(request: LoginRequest) {
    const googleRequest = new GoogleAuthRequest();
    googleRequest.email = request.email;
    googleRequest.password = request.password;
    googleRequest.returnedSecureToken = true;
    this.as.googleLogin(googleRequest).subscribe(
      resp  => {
        console.log(resp);
        if (resp.registered) {
          sessionStorage.setItem('currentUser', resp.idToken);
          console.log(sessionStorage.getItem('currentUser'));
          this.router.navigate(['home']);
        }
      },
      err => console.log(err)
    );
  }
}
