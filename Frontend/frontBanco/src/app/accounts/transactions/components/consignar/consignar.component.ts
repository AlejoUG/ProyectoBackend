import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Movimientos } from '../../model/Movimientos';
import { MovimientoService } from '../../services/movimientos.service';

@Component({
  selector: 'app-consignar',
  templateUrl: './consignar.component.html',
  styleUrls: ['./consignar.component.css']
})
export class ConsignarComponent implements OnInit {
  dateNow = new Date();
  movimientos1:Movimientos = new Movimientos();
  constructor(private router:Router, private service: MovimientoService,
    private toastr: ToastrService,private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  Guardar(){
    const clidcliente = this.activatedRoute.snapshot.params['clidcliente'];
    const numCuenta = this.activatedRoute.snapshot.params['numCuenta'];
    const movimientos={
      pronumCuenta:numCuenta,
      tpMovimiento:"Consignacion",
      monto:this.movimientos1.monto,
      fechaMovimiento: this.movimientos1.fechaMovimiento = formatDate(this.dateNow, 'YYYY-MM-dd hh:mm:ss','en-US'),
      descripcion:"Corresponsal bancario"
      };
      this.service.consignar(movimientos).subscribe(dataBack=>{
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
