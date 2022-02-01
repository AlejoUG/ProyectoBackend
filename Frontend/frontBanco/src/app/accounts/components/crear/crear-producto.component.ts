import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Producto } from '../../model/Producto';
import { ProductoService } from '../../services/producto.service';

@Component({
  selector: 'app-crear-producto',
  templateUrl: './crear-producto.component.html',
  styleUrls: ['./crear-producto.component.css']
})
export class CrearProductoComponent implements OnInit {
  dateNow = new Date();
  producto:Producto=new Producto();
  constructor(private router:Router, private service:ProductoService, private toastr: ToastrService) { }

  ngOnInit(): void {}

  Guardar(){
    let idcliente=localStorage.getItem("idcliente");
    const producto={
      tipCuenta:this.producto.tipCuenta,
      clidcliente:Number(idcliente),
      estado:"Activo",
      saldo:0,
      fechaApertura:this.producto.fechaApertura = formatDate(this.dateNow, 'YYYY-MM-dd hh:mm:ss','en-US'),
    };
    this.service.crearCuentaCliente(producto).subscribe(dataBack=>{
      if(dataBack.success==0){
        this.toastr.success(dataBack.mensaje, 'OK');
        this.router.navigate([`cliente/${producto.clidcliente}/accounts`]);
      }
    },
    err=>{
      this.toastr.error(err.error.mensaje, 'Fail');
      this.router.navigate([`cliente/${producto.clidcliente}/accounts`]);
    })
  }
  Volver6(){
    let idcliente=localStorage.getItem("idcliente");
    this.router.navigate([`cliente/${Number(idcliente)}/accounts`]);}

}
