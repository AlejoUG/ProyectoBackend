import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Clientes } from '../../models/Clientes';
import { ClientesService } from '../../services/clientes.service';

@Component({
  selector: 'app-listar',
  templateUrl: './detallesCliente.component.html',
  styleUrls: ['./detallesCliente.component.css']
})
export class DetallesClientesComponent implements OnInit {
  clientes:Clientes=new Clientes();
  constructor(private service:ClientesService, private router:Router,
    private activatedRoute: ActivatedRoute, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.detalles();
  }

  detalles(){
    const idcliente = this.activatedRoute.snapshot.params['idcliente'];
    this.service.obtenerClientesId(idcliente).subscribe(dataBack=>{
      if(dataBack.success==0){
        this.clientes=dataBack.data;
      }
      else if(dataBack.success==1){
        this.toastr.error(dataBack.mensaje, 'Fail');
        this.router.navigate(["listar"]);
      }
    },
    err=>{
      this.toastr.error(err.error.mensaje, 'Fail');
      this.router.navigate(["listar"]);
    })
  }

  Volver4():void{
    this.router.navigate(["listar"]);
  }

}
