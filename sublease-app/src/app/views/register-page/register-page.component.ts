import { Component, OnInit, ViewChild } from '@angular/core';
import { RegisterFormComponent } from '../../Components/Forms/register-form/register-form.component';
import { AuthService } from '../../Services/Auth/auth.service';
import { FBRegistrationRequest } from '../../Models/FBRegistrationRequest/FBRegistrationRequest';
import { RegistrationRequest } from '../../Models/RegistrationRequest/RegistrationRequest';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  constructor( private authService: AuthService, private router: Router) { }
  @ViewChild(RegisterFormComponent, {static: true})
    private registerComponent: RegisterFormComponent;

  ngOnInit(): void {
  }

  register(request: RegistrationRequest) {
    const googleRequest = new FBRegistrationRequest();
    googleRequest.email = request.email;
    googleRequest.password = request.getPassword();
    googleRequest.returnedSecureToken = true;
    this.authService.googleRegister(googleRequest).subscribe(
      resData => {
        console.log(resData);
        this.router.navigate(['googleLogin']);
      },
      err => console.log(err)
    );
  }

}
