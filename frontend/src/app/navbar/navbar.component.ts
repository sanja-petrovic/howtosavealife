import {Component, NgModule, OnInit} from '@angular/core';
import { User } from '../model/Users';
import {AuthService} from "../services/AuthService";
import { UserService } from '../services/UserService';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css', '../app.component.css']
})
export class NavbarComponent implements OnInit {

  title = 'howtosavealife';
  public user: User;
  isLoggedIn = null;
  constructor(
    private authService: AuthService, private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.tokenIsPresent();
    this.userService.getActiveUser().subscribe((user) => {
      this.user = user;
    });
  }

  logOut() {
    this.authService.logout();
  }

  toHome() {
    this.router.navigate(['/']);
  }
}
