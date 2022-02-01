import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { Observable } from 'rxjs';
import { GeneralResponse } from 'src/app/shared/models/general-response';
import { Movimientos } from '../model/Movimientos';


@Injectable({
  providedIn: 'root'
})

export class MovimientoService{

  constructor(private httpMovimiento:HttpClient) { }

  Url='http://localhost:8080'

  public obtenerMovimientosCuenta(clidcliente:number, pronumCuenta:number): Observable<GeneralResponse<Movimientos[]>>{
    return this.httpMovimiento.get<GeneralResponse<Movimientos[]>>(this.Url+`/customers/${clidcliente}/accounts/${pronumCuenta}/movimientos`);
  }

  public consignar(movimientos:Movimientos): Observable<GeneralResponse<Movimientos>>{
    return this.httpMovimiento.put<GeneralResponse<Movimientos>>(this.Url+`/accounts/${movimientos.pronumCuenta}/add`,movimientos);
  }

  public retirar(movimientos:Movimientos): Observable<GeneralResponse<Movimientos>>{
    return this.httpMovimiento.put<GeneralResponse<number>>(this.Url+`/accounts/${movimientos.pronumCuenta}/withdrawal`,movimientos);
  }

  public transferir(movimientos:Movimientos): Observable<GeneralResponse<Movimientos>>{
    return this.httpMovimiento.put<GeneralResponse<number>>(this.Url+`/accounts/${movimientos.pronumCuenta}/transfer`,movimientos);
  }

}
