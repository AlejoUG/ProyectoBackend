import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Clientes } from 'src/app/customers/models/Clientes';
import { ClientesService } from 'src/app/customers/services/clientes.service';
import { Producto } from '../../model/Producto';
import { ProductoService } from '../../services/producto.service';

@Component({
  selector: 'app-listar-productos',
  templateUrl: './listar-productos.component.html',
  styleUrls: ['./listar-productos.component.css']
})

export class ListarProductosComponent implements OnInit {
  productos: Producto[] = [];
  productos1:Producto=new Producto();
  clientes1:Clientes=new Clientes();

  constructor(private router:Router, private service: ProductoService, private service1: ClientesService,
    private toastr: ToastrService,private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarCuentas();
  }

  Crear(idcliente = this.activatedRoute.snapshot.params['idcliente']){
    localStorage.setItem("idcliente",idcliente.toString());
    this.router.navigate(["accounts/crear"]);
  }

  cargarCuentas():void{
    const idcliente = this.activatedRoute.snapshot.params['idcliente'];
    this.service.obtenerCuentasClientes(idcliente).subscribe(datosBack=>{
      if(datosBack.success==0){
        this.productos = datosBack.data;
      }
      else if(datosBack.success==1){

      }
    }, (err) =>{
      this.toastr.error(err.error.mensaje, 'Fail',{positionClass:'toast-to-center'});
    });
  }

  activar(productos1:Producto){
      this.service.activarCuenta(productos1).subscribe(dataBack=>{
        if(dataBack.success==3){
          this.toastr.success(dataBack.mensaje, 'OK');
        }
        else if(dataBack.success==1){
          this.toastr.error(dataBack.mensaje, 'Fail');
        }
        this.cargarCuentas();
      }, err =>{
        this.toastr.error(err.error.mensaje, 'Fail');
        this.cargarCuentas();
      })
  }

  inactivar(productos1:Producto){
    this.service.inactivarCuenta(productos1).subscribe(dataBack=>{
      if(dataBack.success==3){
        this.toastr.success(dataBack.mensaje, 'OK');
      }
      else if(dataBack.success==1){
        this.toastr.error(dataBack.mensaje, 'Fail');
      }
      this.cargarCuentas();
    }, err =>{
      this.toastr.error(err.error.mensaje, 'Fail');
      this.cargarCuentas();
    })
}
  cancelar(productos1:Producto){
    this.service.cancelarCuenta(productos1).subscribe(dataBack=>{
    if(dataBack.success==3){
      this.toastr.success(dataBack.mensaje, 'OK');
    }
    else if(dataBack.success==1){
      this.toastr.error(dataBack.mensaje, 'Fail');
    }
    this.cargarCuentas();
  }, err =>{
    this.toastr.error(err.error.mensaje, 'Fail');
    this.cargarCuentas();
  })
  }

  Volver5(){
    this.router.navigate(["listar"]);
  }
}
