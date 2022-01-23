package com.backend.api.apirest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.apirest.model.Clientes;
import com.backend.api.apirest.repository.ClientesRepository;

@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	//Obtener clientes
	public List<Clientes> findAll(){
		return clientesRepository.findAll();
	}
	
	//Obtener cliente 
	public Clientes obtenerCliente(Long numIdentificacion) {
		return clientesRepository.getOne(numIdentificacion);	
	}
	
	//Crear cliente
	public void guardar(Clientes clientes){
		clientesRepository.save(clientes);
	}
	
	@Transactional // Modificar datos clientes
	public Clientes save(Clientes clientes) {
		return clientesRepository.save(clientes);
	}
	
	@Transactional //obtener cliente para usar el response entity
	public Optional<Clientes> findByNumIdentificacion(Long numIdentificacion){
		return clientesRepository.findByNumIdentificacion(numIdentificacion);
	}
	
	@Transactional //Eliminar clientes
	public void eliminarClientesCancelados(Long numIdentificacion) {
		clientesRepository.eliminarClientesCancelados(numIdentificacion);
	}
		
	/*public void eliminar(Integer numCuenta) {
		clientesRepository.deleteById(numCuenta);
	}
	
	public List<Clientes> buscarClientesActivos(Integer numCuenta, String estado){
		return clientesRepository.buscarClientesActivos(numCuenta, estado);
	}
	
	/*public void setClientesInfoById(Integer numCuenta) {
		clientesRepository.setClientesInfoById(nombre, apellidos, tpIdentificacion, numIdentificacion, email, fechaNacimiento, numCuenta);
	}*/
	
}
