package com.backend.api.apirest.Service;

import java.util.List;
import java.util.Optional;

import com.backend.api.apirest.Entity.CustomerEntity;
import com.backend.api.apirest.Entity.ProductEntity;

public interface ProductService {
	public List<ProductEntity> listarCuentasCliente(Long clidcliente) throws Exception;
	public ProductEntity crearCuentaCliente(ProductEntity productos) throws Exception;
	public ProductEntity listnumcuenta(Long numCuenta) throws Exception;
	public ProductEntity cambiarestado(ProductEntity productos);
	public ProductEntity actualizarSaldo(ProductEntity productos);
	public boolean cuentaExiste(Long numCuenta) throws Exception;
}
