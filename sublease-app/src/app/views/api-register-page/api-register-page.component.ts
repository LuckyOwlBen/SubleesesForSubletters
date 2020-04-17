import { Component, OnInit } from '@angular/core';
import { RegisterFormComponent } from '../../Components/Forms/register-form/register-form.component';
import { AuthService } from '../../Services/Auth/auth.service';
import { RegistrationRequest } from '../../Models/RegistrationRequest/RegistrationRequest';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-api-register-page',
  templateUrl: './api-register-page.component.html',
  styleUrls: ['./api-register-page.component.css']
})
export class ApiRegisterPageComponent implements OnInit {

  constructor( private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  register(request: RegistrationRequest) {
    this.authService.apiRegister(request).subscribe(
      resData => {
        console.log(resData);
        this.router.navigate(['apiLogin']);
      },
      err => console.log(err)
    );
  }

}
