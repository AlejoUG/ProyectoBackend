import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { InicioComponent } from './inicio/inicio.component';
import { AgregarComponent } from './Clientes/agregar/agregar.component';
import { ModificarComponent } from './Clientes/modificar/modificar.component';
import { BorrarComponent } from './Clientes/borrar/borrar.component';
import { ListarComponent } from './Clientes/listar/listar.component';
import { ClientesService } from './service/clientes.service';
import { ProductoService } from './service/producto.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CrearComponent } from './crear/crear.component';
import { ModificarProductoComponent } from './modificar-producto/modificar-producto.component';

const routesApp:Routes=[];
@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavbarComponent,
    InicioComponent,
    AgregarComponent,
    ModificarComponent,
    BorrarComponent,
    ListarComponent,
    CrearComponent,
    ModificarProductoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routesApp)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
