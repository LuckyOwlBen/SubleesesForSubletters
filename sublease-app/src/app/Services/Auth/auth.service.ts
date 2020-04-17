import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FBRegistrationRequest } from '../../Models/FBRegistrationRequest/FBRegistrationRequest';
import { RegistrationResponse } from '../../Models/RegistrationResponse/RegistrationResponse';
import { GoogleAuthRequest } from '../../Models/GoogleAuthRequest/GoogleAuthRequest';
import { GoogleAuthResponse } from '../../Models/GoogleAuthResponse/GoogleAuthResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private APIKey = 'AIzaSyCuy5NkP02sVDu0mTzX_e9R5TdZ2zXDTVg';
  private FBbaseUrl = `https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=${this.APIKey}`;
  private googleLoginUrl = `https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${this.APIKey}`;
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
  googleLogin(googleAuthRequest: GoogleAuthRequest) {
    return this.http.post<GoogleAuthResponse>( this.googleLoginUrl , googleAuthRequest);
  }

  googleRegister(registrationRequest: FBRegistrationRequest): Observable<RegistrationResponse> {
    return this.http.post<RegistrationResponse>(this.FBbaseUrl, registrationRequest);
  }
}
