package com.backend.api.apirest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.api.apirest.model.Clientes;
import com.backend.api.apirest.model.Movimientos;
import com.backend.api.apirest.repository.MovimientosRepository;

@Service
public class MovimientosService {
	
	@Autowired
	private MovimientosRepository movimientosRepository;
	
	@Transactional //Hacer movimientos clientes
	public void guardar(Movimientos movimientos){
		movimientosRepository.save(movimientos);
	}
	
	@Transactional //Obtener movimientos cliente forma descendente
	public List<Movimientos> buscarMovimientosClientes(Long pronumCuenta){
		return movimientosRepository.buscarMovimientosClientes(pronumCuenta);
	}
	
	/*@Transactional
	public void actualizarConsignacion(Long pronumCuenta){
		movimientosRepository.actualizarConsignacion(pronumCuenta);
	}*/	
}
