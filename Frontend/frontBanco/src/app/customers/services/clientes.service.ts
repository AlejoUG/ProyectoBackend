import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { Observable } from 'rxjs';
import { Clientes } from '../models/Clientes';
import { GeneralResponse } from 'src/app/shared/models/general-response';

@Injectable({
  providedIn: 'root'
})

export class ClientesService{

  constructor(private httpCliente:HttpClient) { }

  Url='http://localhost:8080/customers'

  public obtenerClientes(): Observable<GeneralResponse<Clientes[]>>{
    return this.httpCliente.get<GeneralResponse<Clientes[]>>(this.Url);
  }

  public obtenerClientesId(idcliente:number): Observable<GeneralResponse<Clientes>>{
    return this.httpCliente.get<GeneralResponse<Clientes>>(this.Url+'/'+`${idcliente}`);
  }

  public crearClientes(clientes:Clientes): Observable<GeneralResponse<Clientes>>{
    return this.httpCliente.post<GeneralResponse<Clientes>>(this.Url,clientes);
  }

  public actualizarClientes(clientes:Clientes): Observable<GeneralResponse<Clientes>>{
    return this.httpCliente.put<GeneralResponse<Clientes>>(this.Url+"/"+clientes.idcliente,clientes);
  }

  public eliminarClientes(clientes:Clientes): Observable<GeneralResponse<number>>{
    return this.httpCliente.delete<GeneralResponse<number>>(this.Url+"/"+clientes.idcliente);
  }

  /*public actualizarClientes(clientes:Clientes): Observable<GeneralResponse<Clientes>>{
    return this.httpCliente.put<GeneralResponse<Clientes>>(this.Url+"/"+clientes.idcliente,clientes);
  }*/

  /*public eliminarClientes(idcliente:number): Observable<GeneralResponse<any>>{
    return this.httpCliente.delete<GeneralResponse<number>>(this.Url+"/"+`${idcliente}`);
  }*/

}
