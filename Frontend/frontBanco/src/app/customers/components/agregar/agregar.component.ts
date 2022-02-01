import { Clientes } from 'src/app/customers/models/Clientes';
import { ClientesService } from 'src/app/customers/services/clientes.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { formatDate } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-agregar',
  templateUrl: './agregar.component.html',
  styleUrls: ['./agregar.component.css']
})
export class AgregarComponent implements OnInit {
  dateNow = new Date();
  clientes:Clientes=new Clientes();

  constructor(private router:Router, private service:ClientesService, private toastr: ToastrService) { }
  ngOnInit(): void {
  }

    Guardar(){
      const clientes={
        numIdentificacion: this.clientes.numIdentificacion,
        nombres: this.clientes.nombres,
        apellidos: this.clientes.apellidos,
        tpIdentificacion: this.clientes.tpIdentificacion,
        email: this.clientes.email,
        fechaNacimiento: this.clientes.fechaNacimiento,
        fechaCreacion: this.clientes.fechaCreacion = formatDate(this.dateNow, 'YYYY-MM-dd hh:mm:ss','en-US'),
      };

      this.service.crearClientes(clientes).subscribe(dataBack=>{
        if(dataBack.success==0){
          this.toastr.success(dataBack.mensaje, 'OK');
          this.router.navigate(["listar"]);
        }
      },
      err=>{
        this.toastr.error(err.error.mensaje, 'Fail');
        this.router.navigate(["listar"]);
      })
  }

  Volver2(){
    this.router.navigate(["listar"]);
  }
}
