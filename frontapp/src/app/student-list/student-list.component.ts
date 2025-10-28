import { Component } from '@angular/core';
import { FormService } from '../services/form.service';
import { Router } from '@angular/router';
import { NgFor } from '@angular/common';
@Component({
  selector: 'app-student-list',
  imports: [NgFor],
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent {
  studentDataList: any[] = [];
   
     constructor(private readonly formservice: FormService,private readonly router:Router) {}
   
     ngOnInit() {
       this.loadStudentData();
     }
   
     loadStudentData() {
       this.formservice.getAllStudentData().subscribe({
         next: (data) => {
           this.studentDataList = data;
         },
         error: (error) => {
           console.error('Error fetching Student data:', error);
         }
       });
     }
}
