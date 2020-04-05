import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface AuthResponseData {
  kind: string;
  idToken:string;
  email:string;
  refreshToken: string;
  expiresIn: string;
  localId:string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl:string = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=[API_KEY]"
  constructor(private http:HttpClient) { }

  signUp(email:string, password:string){
    this.http.post(this.baseUrl,{
      email:email,
      password:password,
      returnedSecureToken: true
    }
    );

  }
}
