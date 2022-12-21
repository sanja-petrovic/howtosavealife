import {Injectable} from '@angular/core';
import {map} from 'rxjs/operators';
import {ApiService} from "./ApiService";
import {ConfigService} from "./ConfigService";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Admin, MedicalStaff } from '../model/Users';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http: HttpClient
  ) {
  }

  registerAdmin(admin: Admin){
    return this.apiService.post(`${this.config.admin_url}/register`, admin);
  }
  getAll() {
    return this.apiService.get(this.config.users_url);
  }
  

}