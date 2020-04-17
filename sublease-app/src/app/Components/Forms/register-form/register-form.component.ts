import { Component, Output, EventEmitter } from '@angular/core';
import { User } from '../../../Models/User/user';
import { FBRegistrationRequest } from '../../../Models/FBRegistrationRequest/FBRegistrationRequest';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {

  user: User;
  @Output() eventEmitter = new EventEmitter<FBRegistrationRequest>();


  constructor() { }

  onSubmit(form: NgForm) {
    const request = new FBRegistrationRequest();
    request.email = form.value.email;
    request.password = form.value.password;
    console.log(request);
    this.eventEmitter.next(request);
    form.reset();
  }

}
