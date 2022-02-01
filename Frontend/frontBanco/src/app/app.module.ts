import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InicioComponent } from 'src/app/core/inicio/inicio.component';
import { AgregarComponent } from 'src/app/customers/components/agregar/agregar.component';
import { ModificarComponent } from 'src/app/customers/components/modificar/modificar.component';
import { ListarComponent } from 'src/app/customers/components/listar/listar.component';
import { CrearProductoComponent } from './accounts/components/crear/crear-producto.component';
import { ListarProductoComponent } from './accounts/components/listarproducto/listar-producto.component';
import { ListarProductosComponent } from './accounts/components/listarproductos/listar-productos.component';
import { RetirarComponent } from './accounts/transactions/components/retirar/retirar.component';
import { ConsignarComponent } from './accounts/transactions/components/consignar/consignar.component';
import { TransferirComponent } from './accounts/transactions/components/transferir/transferir.component';
import { DetallesClientesComponent } from './customers/components/detallesCliente/detallesCliente.component';

import { ClientesService } from 'src/app/customers/services/clientes.service';
import { ProductoService } from './accounts/services/producto.service';

import { FooterComponent } from 'src/app/core/footer/footer.component';
import { NavbarComponent } from 'src/app/core/navbar/navbar.component';
import { Navbar1Component } from 'src/app/core/navbar1/navbar1.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

const routesApp:Routes=[];
@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavbarComponent,
    InicioComponent,
    AgregarComponent,
    ModificarComponent,
    ListarComponent,
    DetallesClientesComponent,
    ListarProductosComponent,
    ListarProductoComponent,
    CrearProductoComponent,
    RetirarComponent,
    ConsignarComponent,
    TransferirComponent,
    Navbar1Component,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routesApp),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
