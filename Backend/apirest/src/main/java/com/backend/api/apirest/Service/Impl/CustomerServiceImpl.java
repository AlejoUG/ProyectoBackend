package com.backend.api.apirest.Service.Impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.api.apirest.Entity.CustomerEntity;
import com.backend.api.apirest.Entity.ProductEntity;
import com.backend.api.apirest.Repository.CustomerRepository;
import com.backend.api.apirest.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository clientesRepository;
	
	@Override 
	@Transactional//Obtener clientes
	public List<CustomerEntity> obtenerClientes() throws Exception{
		return clientesRepository.findAll();
	}
	
	@Override 
	@Transactional//Obtener cliente 
	public CustomerEntity obtenerCliente(Long idcliente) throws Exception{
		return clientesRepository.getOne(idcliente);	
	}
	
	@Override 
	@Transactional//Crear cliente
	public CustomerEntity guardarCliente(CustomerEntity clientes) throws Exception{
		return clientesRepository.save(clientes);
	}
	
	@Override
	@Transactional //Modificar datos clientes
	public CustomerEntity modificarCliente(CustomerEntity clientes) throws Exception{
		return clientesRepository.save(clientes);
	}
	
	@Override
	@Transactional //Saber si cliente existe
	public boolean clienteExiste(Long idcliente) throws Exception{
		return clientesRepository.existsById(idcliente);
	}
	
	@Transactional //Eliminar clientes
	public void eliminarClientesCancelados(Long idcliente) throws Exception{
		clientesRepository.deleteById(idcliente);
		//clientesRepository.eliminarClientesCancelados(clidcliente);
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
