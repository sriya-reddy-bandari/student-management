import { Component } from '@angular/core';
import { FormGroup,FormControl,Validators,FormArray, ReactiveFormsModule } from '@angular/forms';
import { FormService } from '../services/form.service';
import { Router } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-category-product',
  imports: [ReactiveFormsModule,NgFor],
  templateUrl: './category-product.component.html',
  styleUrl: './category-product.component.css'
})
export class CategoryProductComponent {
    categoryForm: FormGroup;
     constructor(private readonly formService:FormService,private readonly router:Router) {
        this.categoryForm = new FormGroup({
          cid: new FormControl('', Validators.required),
          title: new FormControl('',Validators.required),
          products: new FormArray([
            new FormGroup({
            pid: new FormControl('', Validators.required),
            productName: new FormControl('', Validators.required),
            })
          ])
        });
        }

        
        removeProduct(index: number) {
          this.products.removeAt(index);
        }

        get products(): FormArray {
            return this.categoryForm.get('products') as FormArray;
          }
        
          addProducts() {
            const productsGroup = new FormGroup({
              pid: new FormControl('', Validators.required),
              productName: new FormControl('', Validators.required)
            });
        
            this.products.push(productsGroup);
          }


          onSubmit() {
            if (this.categoryForm.valid) {
              this.formService.submitCategory(this.categoryForm.value).subscribe(response => {
                console.log('Category saved successfully', response);
                this.categoryForm.reset();
              });
            }
          }
        
          navigateTo(path: string) {
            this.router.navigate([path]);
        }

}

