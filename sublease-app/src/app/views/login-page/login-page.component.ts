import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../../Services/Auth/auth.service';
import { Router } from '@angular/router';
import { GoogleAuthRequest } from '../../Models/GoogleAuthRequest/GoogleAuthRequest';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(private as: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(request: GoogleAuthRequest) {
    this.as.googleLogin(request).subscribe(
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
