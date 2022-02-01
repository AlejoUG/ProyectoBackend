import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Movimientos } from '../../model/Movimientos';
import { MovimientoService } from '../../services/movimientos.service';

@Component({
  selector: 'app-retirar',
  templateUrl: './retirar.component.html',
  styleUrls: ['./retirar.component.css']
})
export class RetirarComponent implements OnInit {
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
      tpMovimiento:"Retiro",
      monto:this.movimientos1.monto,
      fechaMovimiento: this.movimientos1.fechaMovimiento = formatDate(this.dateNow, 'YYYY-MM-dd hh:mm:ss','en-US'),
      descripcion:"Corresponsal bancario"
      };
      this.service.retirar(movimientos).subscribe(dataBack=>{
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
