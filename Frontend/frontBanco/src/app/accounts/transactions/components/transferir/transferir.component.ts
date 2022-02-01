import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Producto } from 'src/app/accounts/model/Producto';
import { ProductoService } from 'src/app/accounts/services/producto.service';
import { Movimientos } from '../../model/Movimientos';
import { MovimientoService } from '../../services/movimientos.service';

@Component({
  selector: 'app-transferir',
  templateUrl: './transferir.component.html',
  styleUrls: ['./transferir.component.css']
})
export class TransferirComponent implements OnInit {
  dateNow = new Date();
  movimientos1:Movimientos = new Movimientos();
  constructor(private router:Router, private service: MovimientoService,
    private toastr: ToastrService,private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  Guardar(){
    const numCuenta = this.activatedRoute.snapshot.params['numCuenta'];
    const clidcliente = this.activatedRoute.snapshot.params['clidcliente'];
    const movimientos={
      pronumCuenta:numCuenta,
      pronumCuenta2:this.movimientos1.pronumCuenta2,
      tpMovimiento:"Transferencia",
      monto:this.movimientos1.monto,
      fechaMovimiento: this.movimientos1.fechaMovimiento = formatDate(this.dateNow, 'YYYY-MM-dd hh:mm:ss','en-US'),
      descripcion:"Transfirio"
      };
      this.service.transferir(movimientos).subscribe(dataBack=>{
        if(dataBack.success==0){
          this.toastr.success(dataBack.mensaje, 'OK');
          this.router.navigate([`/cliente/${clidcliente}/accounts/${numCuenta}/movimientos`]);
        }
        else if(dataBack.success==1){
          this.toastr.error(dataBack.mensaje, 'Fail');
        }
      },
      err=>{
        this.toastr.error(err.error.mensaje, 'Fail');
      })
  }

  Volver2(){
    const numCuenta = this.activatedRoute.snapshot.params['numCuenta'];
    const clidcliente = this.activatedRoute.snapshot.params['clidcliente'];
    this.router.navigate([`/cliente/${clidcliente}/accounts/${numCuenta}/movimientos`]);
  }

}
