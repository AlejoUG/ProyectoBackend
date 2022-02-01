import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from 'src/app/core/inicio/inicio.component';
import { AgregarComponent } from 'src/app/customers/components/agregar/agregar.component';
import { ListarComponent } from 'src/app/customers/components/listar/listar.component';
import { ModificarComponent } from 'src/app/customers/components/modificar/modificar.component';
import { CrearProductoComponent } from './accounts/components/crear/crear-producto.component';
import { ListarProductoComponent } from './accounts/components/listarproducto/listar-producto.component';
import { ListarProductosComponent } from './accounts/components/listarproductos/listar-productos.component';
import { ConsignarComponent } from './accounts/transactions/components/consignar/consignar.component';
import { RetirarComponent } from './accounts/transactions/components/retirar/retirar.component';
import { TransferirComponent } from './accounts/transactions/components/transferir/transferir.component';
import { DetallesClientesComponent } from './customers/components/detallesCliente/detallesCliente.component';

const routes: Routes = [
  {path:'', component:InicioComponent},
  {path:'agregar',component:AgregarComponent},
  {path:'listar',component:ListarComponent},
  {path: 'modificar/:idcliente',component:ModificarComponent},
  {path: 'clientesDetalles/:idcliente',component:DetallesClientesComponent},
  {path: 'cliente/:idcliente/accounts',component:ListarProductosComponent},
  {path: 'cliente/:clidcliente/accounts/:numCuenta/movimientos',component:ListarProductoComponent},
  {path: 'accounts/crear',component:CrearProductoComponent},
  {path: 'cliente/:clidcliente/accounts/:numCuenta/movimientos/consignar',component:ConsignarComponent},
  {path: 'cliente/:clidcliente/accounts/:numCuenta/movimientos/retirar',component:RetirarComponent},
  {path: 'cliente/:clidcliente/accounts/:numCuenta/movimientos/transferir',component:TransferirComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
