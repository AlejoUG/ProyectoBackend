package com.backend.api.apirest.interfaceService;

import java.util.List;

import com.backend.api.apirest.model.Productos;

public interface InterfaceProductos {
	public List<Productos> listclnumIdentificacion(Long clnumIdentificacion );
	public Productos listnumcuenta(Long numCuenta);
	public Productos cambiarestado(Productos productos);
	public Productos actualizarSaldo(Productos productos);	
}
