import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormService } from '../services/form.service';
import { Router,ActivatedRoute} from '@angular/router';
import { AuthorizationServiceService } from '../services/authorization-service.service';

@Component({
  selector: 'app-create-form',
  imports: [CommonModule, ReactiveFormsModule],
  providers:[FormService],
  templateUrl: './create-form.component.html',
  styleUrl: './create-form.component.css'
})
export class CreateFormComponent  implements OnInit{
  createForm: FormGroup;
  isEditable:boolean=false;
  userId:number|null=null;

  constructor(private readonly formservice:FormService,private readonly router:Router,private readonly route:ActivatedRoute,private readonly auth: AuthorizationServiceService){
      this.createForm = new FormGroup({
        id:new FormControl(null),
        name: new FormControl('', Validators.required),
        email: new FormControl('', [Validators.required, Validators.email]),
        mobilenumber: new FormControl('', [
          Validators.required,
          Validators.pattern(/^\d{10}$/)
        ]),
        address: new FormControl('', Validators.required)
      });
    }

    ngOnInit(): void {
        this.route.paramMap.subscribe(params=>
        {
          const userId=params.get("id");
          if(userId){
            this.isEditable=true;
            this.userId=+userId;
            this.updateUser(this.userId);
          }
          
        }
        )
      }

     updateUser(userId: number) {
        this.formservice.getByIdFormData(userId).subscribe({
          next:(userData)=>{
            this.createForm.patchValue(userData);
          }});
        }
    
  
    onSubmit() {
      if (this.createForm.valid) {
        console.log(this.createForm.value);
        if(this.isEditable && this.userId!=null){
            this.formservice.updateFormData(this.userId,this.createForm.value).subscribe({
              next:()=>{
                this.router.navigate(["/list"]);
              }
            });
        }

        else{
        this.formservice.saveFormData(this.createForm.value).subscribe({
          next: (response) => {
            console.log('Form Submitted Successfully:', response);
            console.log(this.createForm.value);
            console.log("reset form");
            this.createForm.reset();
          },
          error: (error) => {
            console.error('Error submitting form:', error);
          }
        });
      }
  }}


  navigateTo(path: string) {
    this.router.navigate([path]);
}
logout() {
  this.auth.logout();
  this.router.navigate(['/']); 
}
}  
      