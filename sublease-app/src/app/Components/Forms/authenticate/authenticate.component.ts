import { Component, OnInit } from '@angular/core';
import { User } from '../../../Models/User/user';
import { RegistrationRequest } from '../../../Models/RegistrationRequest/RegistrationRequest';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../../Services/Auth/auth.service';

@Component({
  selector: 'app-authenticate',
  templateUrl: './authenticate.component.html',
  styleUrls: ['./authenticate.component.css']
})
export class AuthenticateComponent implements OnInit {
  user: User;


  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm){
    const request = new RegistrationRequest();
    request.email = form.value.email;
    request.setPassword(form.value.password);
    request.firstName = form.value.firstname;
    request.lastName = form.value.lastname;
    this.authService.register(request).subscribe(
      resData => console.log(resData),
      err => console.log(err)
    );
    form.reset();
  }

}
