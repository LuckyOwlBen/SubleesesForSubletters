import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-authenticate',
  templateUrl: './authenticate.component.html',
  styleUrls: ['./authenticate.component.css']
})
export class AuthenticateComponent implements OnInit {
  user:User;
  

  constructor(private authService:AuthService) { }

  ngOnInit() {
  }

  onSubmit(form:NgForm){
    const email = form.value.email;
    const password = form.value.password;
    console.log(form.value)
    this.authService.signUp(email, password).subscribe(
      resData => console.log(resData),
      err => console.log(err)
    )
    form.reset()
  }

}
