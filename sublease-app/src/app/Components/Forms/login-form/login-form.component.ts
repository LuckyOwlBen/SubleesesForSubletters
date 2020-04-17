import { Component, Output, EventEmitter } from '@angular/core';
import { NgForm, FormsModule , ReactiveFormsModule } from '@angular/forms';
import { GoogleAuthRequest } from '../../../Models/GoogleAuthRequest/GoogleAuthRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent  {

  constructor(formsModule: FormsModule, reactiveForms: ReactiveFormsModule) { }
  @Output() eventEmitter = new EventEmitter<GoogleAuthRequest>();


  onSubmit(form: NgForm) {
    const authRequest = new GoogleAuthRequest();
    authRequest.email = form.value.email;
    authRequest.password = form.value.password;
    console.log(authRequest);
    this.eventEmitter.next(authRequest);
    form.
    form.reset();
    return;
  }

}
