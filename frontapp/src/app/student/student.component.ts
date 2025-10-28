import { Component } from '@angular/core';
import { FormService } from '../services/form.service';
import{ FormGroup, FormControl, Validators,FormArray,ReactiveFormsModule} from '@angular/forms';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-student',
  imports: [NgFor,ReactiveFormsModule],
  providers:[FormService],
  templateUrl: './student.component.html',
  styleUrl: './student.component.css'
})
export class StudentComponent {
  studentForm: FormGroup;

  constructor(private readonly formService:FormService,private readonly router:Router) {
    this.studentForm = new FormGroup({
      studentName: new FormControl('', Validators.required),
      about: new FormControl(''),
      laptop: new FormGroup({
        laptopId: new FormControl('', Validators.required),
        modelNumber: new FormControl('', Validators.required),
        brand: new FormControl('', Validators.required)
      }),
      addressList: new FormArray([
        new FormGroup({
          addressId: new FormControl('', Validators.required),
          street: new FormControl('', Validators.required),
          city: new FormControl('', Validators.required),
          country: new FormControl('', Validators.required)
        })
      ])
    });
  }

  removeAddress(index: number) {
    this.addressList.removeAt(index);
  }

  get addressList(): FormArray {
    return this.studentForm.get('addressList') as FormArray;
  }

  addAddress() {
    const addressGroup = new FormGroup({
      addressId: new FormControl('', Validators.required),
      street: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      country: new FormControl('', Validators.required)
    });

    this.addressList.push(addressGroup);
  }

  onSubmit() {
    if (this.studentForm.valid) {
      this.formService.submitStudent(this.studentForm.value).subscribe(response => {
        console.log('Student saved successfully', response);
        this.studentForm.reset();
      });
    }
  }

  navigateTo(path: string) {
    this.router.navigate([path]);
}
}
     