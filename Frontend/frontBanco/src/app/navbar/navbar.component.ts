import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private router:Router) { }

  ngOnInit(): void {
    this.router.navigate(['']);
  }

  List(){
    this.router.navigate(["listar"]);    
  }

  New(){
    this.router.navigate(["agregar"]);
  }

}
