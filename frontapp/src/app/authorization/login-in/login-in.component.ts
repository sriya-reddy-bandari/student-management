import { Component } from '@angular/core';
import { AuthorizationServiceService } from '../../services/authorization-service.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-login-in',
  imports: [FormsModule,NgIf],
  templateUrl: './login-in.component.html',
  styleUrl: './login-in.component.css'
})
export class LoginInComponent {
    loginRequest={username:"",password:""};

    errorMessage="";

    constructor(private readonly auth:AuthorizationServiceService,private readonly router:Router){}


    login(){ 
      this.auth.login(this.loginRequest).subscribe({
        next:(res:any)=>{
          this.auth.storeToken(res.jwtToken);
          this.router.navigate(["/create-form"]);
        },
        error: () => {
          this.errorMessage = 'Invalid Credentials';
        }
      });

    }

    navigateTo(path: string) {
      this.router.navigate([path]);
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/']); 
  }
  }  
