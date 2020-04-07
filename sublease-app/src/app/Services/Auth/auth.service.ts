import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistrationRequest } from '../../Models/RegistrationRequest/RegistrationRequest';
import { RegistrationResponse } from '../../Models/RegistrationResponse/RegistrationResponse';

interface AuthResponseData {
  kind: string;
  idToken: string;
  email: string;
  refreshToken: string;
  expiresIn: string;
  localId: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // APIKey:string = 'AIzaSyCuy5NkP02sVDu0mTzX_e9R5TdZ2zXDTVg';
  // baseUrl:string = `https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=${this.APIKey}`;
  baseUrl = 'http://localhost:8080/user/register';
  constructor(private http: HttpClient) { }

  // signUp(email:string, password:string){
  //   return this.http.post(this.baseUrl,{
  //     email:email,
  //     password:password,
  //     returnedSecureToken: true
  //   }
  //   );

  // }
  register(registrationRequest: RegistrationRequest): Observable<RegistrationResponse> {
    console.log(registrationRequest);
    return this.http.post<RegistrationResponse>(`${this.baseUrl}`, {
      registrationRequest: RegistrationRequest
    });
  }
}
