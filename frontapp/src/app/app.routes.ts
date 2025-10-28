import { Routes } from '@angular/router';
import { CreateFormComponent } from './create-form/create-form.component';
import { ListFormComponent } from './list-form/list-form.component';
import { LoginInComponent } from './authorization/login-in/login-in.component';
import { SignUpComponent } from './authorization/sign-up/sign-up.component';
import { authGuard } from './auth.guard';
import { StudentComponent } from './student/student.component';
import { StudentListComponent } from './student-list/student-list.component';
import { CategoryProductComponent } from './category-product/category-product.component';
import { CategoryListComponent } from './category-list/category-list.component';

export const routes: Routes = [
  {path:'', component:LoginInComponent},
  {path:'sign-in',component:SignUpComponent},
  { path: 'create-form', component: CreateFormComponent , canActivate: [authGuard] },
  { path: 'list', component: ListFormComponent , canActivate: [authGuard] },
  { path: 'edit/:id', component: CreateFormComponent, canActivate: [authGuard]   },
  { path: 'student',component:StudentComponent,canActivate:[authGuard]},
  { path: 'studentList',component:StudentListComponent,canActivate:[authGuard]},
  { path: 'categories',component:CategoryProductComponent,canActivate:[authGuard]},
  { path: 'categoryList',component:CategoryListComponent,canActivate:[authGuard]},
];
