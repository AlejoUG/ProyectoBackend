import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Producto } from '../Modelo/Producto';

@Injectable({
  providedIn: 'root'
})

export class ProductoService{

  constructor(private http:HttpClient) { }

  Url='http://localhost:8080/api/clientes/productos'

  
  
}