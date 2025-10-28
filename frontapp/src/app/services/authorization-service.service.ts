import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationServiceService {
   
  private readonly apiUrl = environment.base_url;
  
  constructor(private readonly http: HttpClient) {  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login_in`, loginRequest);
  }

  signIn(user:any): Observable<any>{
    return this.http.post(`${this.apiUrl}/sign_in`, user,{ responseType: 'text' });
  }

  storeToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout() {
    localStorage.removeItem('token');
  }
}
