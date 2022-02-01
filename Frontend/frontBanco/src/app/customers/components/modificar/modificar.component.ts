import { Component, OnInit } from '@angular/core';
import { Clientes } from 'src/app/customers/models/Clientes';
import { ClientesService } from 'src/app/customers/services/clientes.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-modificar',
  templateUrl: './modificar.component.html',
  styleUrls: ['./modificar.component.css']
})
export class ModificarComponent implements OnInit {
  clientes:Clientes=new Clientes();
  constructor(private service:ClientesService, private router:Router,
    private activatedRoute: ActivatedRoute, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.Actualizar1();
  }

  Actualizar1(){
    const idcliente = this.activatedRoute.snapshot.params['idcliente'];
    this.service.obtenerClientesId(idcliente).subscribe(dataBack=>{
      if(dataBack.success==0){
        this.clientes=dataBack.data;
        console.log(dataBack.data);
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

  Actualizar(clientes:Clientes){
    this.service.actualizarClientes(this.clientes).subscribe(dataBack=>{
       if(dataBack==0){
          this.toastr.success(dataBack.mensaje, 'OK');
          this.router.navigate(["listar"]);
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

  Volver3(){
    this.router.navigate(["listar"]);
  }

}
