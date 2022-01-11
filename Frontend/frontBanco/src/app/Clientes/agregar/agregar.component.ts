import { Clientes } from 'src/app/Modelo/Clientes';
import { ClientesService } from 'src/app/service/clientes.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { formatDate } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-agregar',
  templateUrl: './agregar.component.html',
  styleUrls: ['./agregar.component.css']
})
export class AgregarComponent implements OnInit {
  dateNow = new Date();
  clientes:Clientes=new Clientes();

  constructor(private router:Router, private servicio:ClientesService) { }
  ngOnInit(): void {
  }  

    Guardar(){
      const data={
        numIdentificacion: this.clientes.numIdentificacion,
        nombres: this.clientes.nombres,
        apellidos: this.clientes.apellidos,
        tpIdentificacion: this.clientes.tpIdentificacion,
        email: this.clientes.email,
        fechaNacimiento: this.clientes.fechaNacimiento,
        fechaCreacion: this.clientes.fechaCreacion = formatDate(this.dateNow, 'YYYY-MM-dd hh:mm:ss','en-US'),
        contrasenia:this.clientes.contrasenia,
      };

      this.servicio.createPersona(this.clientes)
      .subscribe(data=>{
      alert("Se agregó con exito...!!");
      this.router.navigate(["listar"]);
      })
  }

  

}
