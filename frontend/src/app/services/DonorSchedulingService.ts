import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './ApiService';
import { ConfigService } from './ConfigService';

@Injectable({
  providedIn: 'root'
})
export class DonorSchedulingService {
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(
    private apiService: ApiService,
    private config: ConfigService) { }

    getFreeBanks(dto: any): Observable<any> {
      return this.apiService.post(`${this.config.available_bank_url}`, dto);
    }

    createAppointment(appointment: any): Observable<any> {
      return this.apiService.post(`${this.config.new_scheduling_url}`, appointment);
    }
}
