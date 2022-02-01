package com.backend.api.apirest.Service.Impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.api.apirest.Entity.CustomerEntity;
import com.backend.api.apirest.Entity.ProductEntity;
import com.backend.api.apirest.Repository.ProductRepository;
import com.backend.api.apirest.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional//Guardar cuenta
	public ProductEntity crearCuentaCliente(ProductEntity productos) throws Exception{
		return productRepository.save(productos);
	}
	
	@Override
	@Transactional//Listar productos clientes
	public List<ProductEntity> listarCuentasCliente(Long clidcliente) throws Exception{
		return productRepository.findByClidcliente(clidcliente);
	}
	
	@Override
	@Transactional//Listar num cuenta
	public ProductEntity listnumcuenta(Long numCuenta) throws Exception{
		return productRepository.getOne(numCuenta);	
	}
	
	@Override
	@Transactional //Modificar estado cuenta
	public ProductEntity cambiarestado(ProductEntity productos){
		return productRepository.save(productos);
	}
	
	@Override
	@Transactional //Modificar saldo cuenta
	public ProductEntity actualizarSaldo(ProductEntity productos) {
		return productRepository.save(productos);
	}
	
	@Override
	@Transactional //Saber si cliente existe
	public boolean cuentaExiste(Long numCuenta) throws Exception{
		return productRepository.existsById(numCuenta);
	}
	
	
	/*@Transactional
	public String verificarProductosCliente(Long clidcliente)  {
		//clientesRepository.eliminarClientesCancelados(numIdentificacion);
		System.out.println(productRepository.verificarEstado(clidcliente));
		return productRepository.verificarEstado(clidcliente);
		/*boolean estado;
		for (int  i=0; i<=lista.size(); i++) {
			ProductEntity productoDetalles=lista.get(i);
			if(productoDetalles.getEstado().equals("Activo") || productoDetalles.getEstado().equals("Inactivo")) {
				return true;
			}
			else {System.out.println(productoDetalles.getEstado());}
		}
	}*/
	/*@Transactional
	public boolean verificarEstadoCuentas (Long clidcliente) {
		int estado=0;
		estado=productRepository.verificarEstado(clidcliente);
		System.out.println(estado);
		return estado;
	}*/
}
