package com.backend.api.apirest.controller;

import java.util.List;

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
	
	public List<Clientes> obtenertodos(){
		return clientesRepository.findAll();
	}
	
	public Clientes obtenerCliente(Integer numCuenta) {
		return clientesRepository.getOne(numCuenta);	
	}
	
	public void actualizar(Clientes clientes) {
		clientesRepository.save(clientes);
	}
	
	public void eliminar(Integer numCuenta) {
		clientesRepository.deleteById(numCuenta);
	}
	
	public List<Clientes> buscarClientesActivos(Integer numCuenta, String estado){
		return clientesRepository.buscarClientesActivos(numCuenta, estado);
	}
	
	public void setClientes(String nombre, String apellidos, String tpIdentificacion, Integer numIdentificacion,
			String email, String fechaNacimiento, Integer numCuenta) {
		clientesRepository.setClientesInfoById(nombre, apellidos, tpIdentificacion, numIdentificacion, email, fechaNacimiento, numCuenta);;
	}
	
}
