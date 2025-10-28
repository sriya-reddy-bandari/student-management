import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class FormService {
  private readonly apiUrl = environment.base_url;

  constructor(private readonly http: HttpClient) {}

  saveFormData(formData: any): Observable<any> {
    const token=localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      "Content-Type": "application/json"
    });
    return this.http.post(`${this.apiUrl}/save`, formData, {
      headers,
      responseType: 'text'
    });
  }

  getAllFormData(): Observable<any[]> {
    const token=localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  return this.http.get<any[]>(`${this.apiUrl}/all`,{headers});
}

  getByIdFormData(id: number): Observable<any> {
    const token=localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get(`${this.apiUrl}/get/${id}`,{headers});
  }


  updateFormData(userId: number, formData: any): Observable<any> {
    const token=localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  return this.http.put(`${this.apiUrl}/update/${userId}`, formData,{headers});
}

  deleteFormData(userId:number):Observable <any>{
    const token=localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.delete(`${this.apiUrl}/delete/${userId}`,{headers,responseType: 'text'});
  }

  submitStudent(StudentData:any):Observable<any> {
    const token=localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      "Content-Type": "application/json"
    });
    return this.http.post(`${this.apiUrl}/saveStudent`, StudentData, {
      headers,
      responseType: 'text'
    });
  }


  getAllStudentData(): Observable<any[]> {
    const token=localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  return this.http.get<any[]>(`${this.apiUrl}/allStudents`,{headers});
}


submitCategory(CategoryData:any):Observable<any> {
  const token=localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`,
    "Content-Type": "application/json"
  });
  return this.http.post(`${this.apiUrl}/saveCategory`, CategoryData, {
    headers,
    responseType: 'text'
  });
}

submitProduct(ProductData:any):Observable<any> {
  const token=localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`,
    "Content-Type": "application/json"
  });
  return this.http.post(`${this.apiUrl}/saveProduct`, ProductData, {
    headers,
    responseType: 'text'
  });
}

getAllCategoryData(): Observable<any[]> {
  const token=localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });
return this.http.get<any[]>(`${this.apiUrl}/allCategories`,{headers});
}

getAllProductData(): Observable<any[]> {
  const token=localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });
return this.http.get<any[]>(`${this.apiUrl}/allProducts`,{headers});
}


}
