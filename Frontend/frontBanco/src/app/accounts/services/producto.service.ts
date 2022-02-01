import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { Observable } from 'rxjs';
import { Producto } from 'src/app/accounts/model/Producto';
import { GeneralResponse } from 'src/app/shared/models/general-response';


@Injectable({
  providedIn: 'root'
})

export class ProductoService{

  constructor(private httpProducto:HttpClient) { }

  Url='http://localhost:8080'

  public obtenerCuentasClientes(clidcliente:number): Observable<GeneralResponse<Producto[]>>{
    return this.httpProducto.get<GeneralResponse<Producto[]>>(this.Url+`/customers/${clidcliente}/accounts`);
  }

  public obtenerCuentaClientesId(clidcliente:number, numCuenta:number): Observable<GeneralResponse<Producto>>{
    return this.httpProducto.get<GeneralResponse<Producto>>(this.Url+`/customers/${clidcliente}/accounts/${numCuenta}`);
  }

  public crearCuentaCliente(producto:Producto): Observable<GeneralResponse<Producto>>{
    return this.httpProducto.post<GeneralResponse<Producto>>(this.Url+'/accounts',producto);
  }

  public activarCuenta(producto:Producto): Observable<GeneralResponse<Producto>>{
    return this.httpProducto.put<GeneralResponse<Producto>>(this.Url+`/accounts/${producto.numCuenta}/activate`,producto);
  }

  public inactivarCuenta(producto:Producto): Observable<GeneralResponse<Producto>>{
    return this.httpProducto.put<GeneralResponse<number>>(this.Url+`/accounts/${producto.numCuenta}/inactivate`,producto);
  }

  public cancelarCuenta(producto:Producto): Observable<GeneralResponse<Producto>>{
    return this.httpProducto.put<GeneralResponse<number>>(this.Url+`/accounts/${producto.numCuenta}/cancel`,producto);
  }

}
