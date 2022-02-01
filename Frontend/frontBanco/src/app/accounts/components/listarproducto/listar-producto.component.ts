import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Producto } from '../../model/Producto';
import { ProductoService } from '../../services/producto.service';
import { Movimientos } from '../../transactions/model/Movimientos';
import { MovimientoService } from '../../transactions/services/movimientos.service';

@Component({
  selector: 'app-modificar-producto',
  templateUrl: './listar-producto.component.html',
  styleUrls: ['./listar-producto.component.css']
})
export class ListarProductoComponent implements OnInit {
  productos1:Producto=new Producto();
  movimientos1:Movimientos[] = [];
  constructor(private router:Router, private service: ProductoService, private service1: MovimientoService,
    private toastr: ToastrService,private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.obtenerMovimientos();
    this.obtenerInfoCuenta();
  }

  obtenerMovimientos():void{
    const clidcliente = this.activatedRoute.snapshot.params['clidcliente'];
    const numCuenta = this.activatedRoute.snapshot.params['numCuenta'];
    this.service1.obtenerMovimientosCuenta(clidcliente,numCuenta).subscribe(datosBack=>{
      if(datosBack.success==0){
        this.movimientos1 = datosBack.data;
      }
      else if(datosBack.success==1){

      }
    }, (err) =>{
      this.toastr.error(err.error.mensaje, 'Fail',{positionClass:'toast-to-center'});
    });
  }

  obtenerInfoCuenta(){
    const clidcliente = this.activatedRoute.snapshot.params['clidcliente'];
    const numCuenta = this.activatedRoute.snapshot.params['numCuenta'];
    this.service.obtenerCuentaClientesId(clidcliente, Number(numCuenta)).subscribe(datosBack=>{
      if(datosBack.success==0){
        this.productos1 = datosBack.data;
        console.log(datosBack.data);
      }
      else if(datosBack.success==1){

      }
    }, (err) =>{
      this.toastr.error(err.error.mensaje, 'Fail',{positionClass:'toast-to-center'});
    });
  }

  Volver4(){
    this.router.navigate([`cliente/${Number(this.productos1.clidcliente)}/accounts`]);
  }
}
