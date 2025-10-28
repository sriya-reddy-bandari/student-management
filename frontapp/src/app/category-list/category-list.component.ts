import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormService } from '../services/form.service';


@Component({
  selector: 'app-category-list',
  imports: [NgFor,NgIf],
  providers:[FormService],
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.css'
})
export class CategoryListComponent {
     categoryDataList: any[] = [];
        
          constructor(private readonly formservice: FormService) {}
        
          ngOnInit() {
            this.loadCategoryData();
          }
        
          loadCategoryData() {
            this.formservice.getAllCategoryData().subscribe({
              next: (data) => {
                this.categoryDataList = data;
              },
              error: (error) => {
                console.error('Error fetching Category data:', error);
              }
            });
          }
     }
     

