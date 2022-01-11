import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { AgregarComponent } from './Clientes/agregar/agregar.component';
import { ListarComponent } from './Clientes/listar/listar.component';
import { ModificarComponent } from './Clientes/modificar/modificar.component';

const routes: Routes = [
  {path:'', component:InicioComponent},
  {path:'agregar',component:AgregarComponent},
  {path:'listar',component:ListarComponent},
  {path: 'modificar',component:ModificarComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
