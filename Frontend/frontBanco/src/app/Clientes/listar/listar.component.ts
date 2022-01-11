import { Component, OnInit } from '@angular/core';
import { Clientes } from 'src/app/Modelo/Clientes';
import { ClientesService } from 'src/app/service/clientes.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {
  clientes1:Clientes[]=[];
  constructor(private service:ClientesService, private router:Router) { }

  ngOnInit(): void {
    this.service.getPersonas()
    .subscribe(data=>{
      this.clientes1=data;
      console.log(data);
    })
  }

}
