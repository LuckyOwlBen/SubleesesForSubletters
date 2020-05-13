import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  searchControl = new FormControl('');
  constructor(private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.router.navigate(['apiLogin']);
  }

}
