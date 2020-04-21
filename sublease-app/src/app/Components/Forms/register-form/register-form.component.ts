import { Component, Output, EventEmitter } from '@angular/core';
import { User } from '../../../Models/User/user';
import { RegistrationRequest } from '../../../Models/RegistrationRequest/RegistrationRequest';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {

  user: User;
  @Output() eventEmitter = new EventEmitter<RegistrationRequest>();


  constructor() { }

  onSubmit(form: NgForm) {
    const request = new RegistrationRequest();
    request.firstName = form.value.firstname;
    request.lastName = form.value.lastName;
    request.email = form.value.email;
    request.setPassword(form.value.password);
    console.log(request);
    this.eventEmitter.next(request);
    form.reset();
  }

}
