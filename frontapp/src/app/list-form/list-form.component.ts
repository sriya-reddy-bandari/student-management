import { Component,OnInit} from '@angular/core';
import { FormService } from '../services/form.service';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';

@Component({
  selector: 'app-list-form',
  imports: [CommonModule],
  providers:[FormService],
  templateUrl: './list-form.component.html',
  styleUrl: './list-form.component.css'
})
export class ListFormComponent implements OnInit{
   
  formDataList: any[] = [];

  constructor(private readonly formservice: FormService,private readonly router:Router) {}

  ngOnInit() {
    this.loadFormData();
  }

  loadFormData() {
    this.formservice.getAllFormData().subscribe({
      next: (data) => {
        this.formDataList = data;
      },
      error: (error) => {
        console.error('Error fetching form data:', error);
      }
    });
  }


  editUser(user_id:number){
    this.router.navigate(["/edit",user_id]);
  
  }

  deleteUser(user_id:number){
    this.formservice.deleteFormData(user_id).subscribe({
       next:()=>{
        this.loadFormData();
       } 
    });
  }

}
