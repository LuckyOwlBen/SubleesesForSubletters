import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FBRegistrationRequest } from '../../Models/FBRegistrationRequest/FBRegistrationRequest';
import { RegistrationRequest } from '../../Models/RegistrationRequest/RegistrationRequest';
import { RegistrationResponse } from '../../Models/RegistrationResponse/RegistrationResponse';
import { GoogleAuthRequest } from '../../Models/GoogleAuthRequest/GoogleAuthRequest';
import { GoogleAuthResponse } from '../../Models/GoogleAuthResponse/GoogleAuthResponse';
import { LoginRequest } from '../../Models/LoginRequest/LoginRequest';
import { LoginResponse } from '../../Models/LoginResponse/LoginResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private APIKey = 'AIzaSyCuy5NkP02sVDu0mTzX_e9R5TdZ2zXDTVg';
  private FBbaseUrl = `https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=${this.APIKey}`;
  private googleLoginUrl = `https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${this.APIKey}`;
  private baseUrl = 'http://localhost:8080';
  private registerUrl = '/auth/register';
  private loginUrl = '/auth/login';
  constructor(private http: HttpClient) { }

  googleLogin(googleAuthRequest: GoogleAuthRequest) {
    return this.http.post<GoogleAuthResponse>( this.googleLoginUrl , googleAuthRequest);
  }

  googleRegister(registrationRequest: FBRegistrationRequest): Observable<RegistrationResponse> {
    return this.http.post<RegistrationResponse>(this.FBbaseUrl, registrationRequest);
  }

  apiRegister(registrationRequest: RegistrationRequest): Observable<RegistrationResponse> {
    return this.http.post<RegistrationResponse>(this.baseUrl + this.registerUrl, registrationRequest);
  }

  apiLogin(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.baseUrl + this.loginUrl, loginRequest);
  }
}
