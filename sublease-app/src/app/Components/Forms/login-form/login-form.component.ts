import { Component, Output, EventEmitter } from '@angular/core';
import { NgForm, FormsModule , ReactiveFormsModule } from '@angular/forms';
import { LoginRequest } from '../../../Models/LoginRequest/LoginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent  {

  constructor(formsModule: FormsModule, reactiveForms: ReactiveFormsModule) { }
  @Output() eventEmitter = new EventEmitter<LoginRequest>();


  onSubmit(form: NgForm) {
    const authRequest = new LoginRequest();
    authRequest.email = form.value.email;
    authRequest.password = form.value.password;
    console.log(authRequest);
    this.eventEmitter.next(authRequest);
    form.reset();
    return;
  }

}
