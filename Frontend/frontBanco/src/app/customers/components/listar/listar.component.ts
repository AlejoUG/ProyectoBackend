import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Clientes } from '../../models/Clientes';
import { ClientesService } from '../../services/clientes.service';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {
  clientes1: Clientes[] = [];
  constructor(private router:Router, private service:ClientesService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.cargarclientes();
  }

  cargarclientes():void{
    this.service.obtenerClientes().subscribe(datosBack=>{
      if(datosBack.success==0){
        this.clientes1 = datosBack.data;
      }
    }, (err) =>{
      this.toastr.error(err.error.mensaje, 'Fail',{positionClass:'toast-to-center'});
    })
  }

  Eliminar(clientes:Clientes){
    this.service.eliminarClientes(clientes).subscribe(dataBack=>{
      if(dataBack.success==0){
        this.clientes1=this.clientes1.filter(p=>p!==clientes);
        this.toastr.success(dataBack.mensaje, 'OK');
        this.cargarclientes();
      }
      else if(dataBack.success==1){
        this.toastr.error(dataBack.mensaje, 'Fail');
      }
    }, err =>{
      this.toastr.error(err.error.mensaje, 'Fail');
    })
  }

  Registrar(){
    this.router.navigate(["agregar"]);
  }

  Volver1(){
    this.router.navigate([""]);
  }

}
