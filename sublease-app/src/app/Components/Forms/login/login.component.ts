import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../../Services/Auth/auth.service';
import { Router } from '@angular/router';
import { GoogleAuthRequest } from '../../../Models/GoogleAuthRequest/GoogleAuthRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private as: AuthService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm){
    const authRequest = new GoogleAuthRequest();
    authRequest.email = form.value.email;
    authRequest.password = form.value.password;
    this.as.googleLogin(authRequest).subscribe(
      resp  => {
        console.log(resp);
        if (resp.registered) {
          sessionStorage.setItem('currentUser', resp.idToken);
          console.log(sessionStorage.getItem('currentUser'));
          this.router.navigate(['home']);
        } else {
          return;
        }
      },
      err => console.log(err)
    );
    form.reset();
  }

}
