import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Clientes } from '../Modelo/Clientes';

@Injectable({
  providedIn: 'root'
})

export class ClientesService{

  constructor(private http:HttpClient) { }

  Url='http://localhost:8080/api/clientes'

  getPersonas(){
    return this.http.get<Clientes[]>(this.Url);
  }

  createPersona(clientes:Clientes){
    return this.http.post<Clientes>(this.Url,clientes);
  }

}
