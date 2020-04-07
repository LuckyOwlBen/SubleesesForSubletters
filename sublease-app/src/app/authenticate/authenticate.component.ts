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
    const firstName = form.value.firstname;
    const lastName = form.value.lastname;
    this.authService.register(email, password, firstName, lastName).subscribe(
      resData => console.log(resData),
      err => console.log(err)
    )
    form.reset()
  }

}
