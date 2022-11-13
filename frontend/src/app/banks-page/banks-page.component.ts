import { Component, OnInit } from '@angular/core';
import {BloodBankService} from "../services/BloodBankService";
import axios from 'axios';
@Component({
  selector: 'app-banks-page',
  templateUrl: './banks-page.component.html',
  styleUrls: ['./banks-page.component.css']
})
export class BanksPageComponent implements OnInit {

  public banks: any[] = [];
  public searchCriteria: string[] = [];

  getBanks(): void {
    this.bloodBankService.getAll().subscribe(data => {
      this.banks = data;
    })
  }

  searchBanks(sortCriteria: string): void {
    const sort: string[] = sortCriteria.split('-');
    const dto = {
      searchCriteria: this.searchCriteria,
      sortCriteria: {
        direction: sort[1],
        property: sort[0]
      }
    }
    this.bloodBankService.searchSort(dto).subscribe(data => {
      console.log(data);
      this.banks = data;
    })
  }

  constructor(private bloodBankService: BloodBankService) { }

  ngOnInit(): void {
      this.getBanks();
  }


}
