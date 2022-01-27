package com.backend.api.apirest.Service;

import java.util.List;
import java.util.Optional;

import com.backend.api.apirest.Entity.CustomerEntity;
import com.backend.api.apirest.Entity.ProductEntity;

public interface CustomerService {
	
	public List<CustomerEntity> obtenerClientes() throws Exception;
	public CustomerEntity obtenerCliente(Long idcliente) throws Exception;
	public CustomerEntity guardarCliente(CustomerEntity clientes) throws Exception;
	public CustomerEntity modificarCliente(CustomerEntity clientes) throws Exception;
	public boolean clienteExiste(Long idcliente) throws Exception;
	//public int verificarProductosCliente(List<ProductEntity> lista);
	
}
