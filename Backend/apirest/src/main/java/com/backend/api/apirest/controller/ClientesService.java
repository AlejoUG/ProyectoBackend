package com.backend.api.apirest.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.apirest.model.Clientes;

@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	public void guardar(Clientes clientes){
		clientesRepository.save(clientes);
	}
		
	public Clientes obtenerCliente(Integer numCuenta) {
		return clientesRepository.getOne(numCuenta);	
	}
	
	@Transactional // modificar datos clientes
	public Clientes save(Clientes clientes) {
		return clientesRepository.save(clientes);
	}
	
	public Optional<Clientes> findByNumCuenta(Integer numCuenta){
		return clientesRepository.findByNumCuenta(numCuenta);
	}
	
	public void eliminar(Integer numCuenta) {
		clientesRepository.deleteById(numCuenta);
	}
	
	public List<Clientes> buscarClientesActivos(Integer numCuenta, String estado){
		return clientesRepository.buscarClientesActivos(numCuenta, estado);
	}
	
	
	
	/*public void setClientesInfoById(Integer numCuenta) {
		clientesRepository.setClientesInfoById(nombre, apellidos, tpIdentificacion, numIdentificacion, email, fechaNacimiento, numCuenta);
	}*/
	
}
