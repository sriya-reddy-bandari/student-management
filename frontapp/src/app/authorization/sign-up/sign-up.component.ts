import { Component } from '@angular/core';
import { AuthorizationServiceService } from '../../services/authorization-service.service';
import { FormsModule} from '@angular/forms';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-sign-up',
  imports: [FormsModule,NgIf],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
   user = { username:"",password:"",role:""};
   message="";

   constructor(private readonly auth: AuthorizationServiceService){}
      
       signIn() {
          this.auth.signIn(this.user).subscribe(resp=>{
            this.message=resp;
          });

       }
       }


