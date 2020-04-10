import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../Services/Auth/auth.service';
import { Router } from '@angular/router';
import {MatLabel} from '@angular/material/form-field';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private as:AuthService, private router:Router) { }

  ngOnInit() {
  }

  onSubmit(form:NgForm){
    const email = form.value.email;
    const password = form.value.password;
    this.as.login(email, password).subscribe(
      resp  => {
        console.log(resp);
        if(resp.registered){
          sessionStorage.setItem("currentUser", resp.idToken);
          console.log(sessionStorage.getItem("currentUser"));
          this.router.navigate(['home']);
        }else{
          return;
        }
      },
      err => console.log(err)
    )
    form.reset();


  }

}
