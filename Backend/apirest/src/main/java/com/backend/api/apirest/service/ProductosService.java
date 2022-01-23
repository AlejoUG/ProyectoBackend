package com.backend.api.apirest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.api.apirest.interfaceService.InterfaceProductos;
import com.backend.api.apirest.model.Clientes;
import com.backend.api.apirest.model.Productos;
import com.backend.api.apirest.repository.ProductosRepository;

@Service
public class ProductosService implements InterfaceProductos{
	
	@Autowired
	private ProductosRepository productosRepository;
	
	@Transactional//Guardar cuenta
	public void guardar(Productos productos){
		productosRepository.save(productos);
	}
	
	@Transactional//Listar productos clientes
	public List<Productos> listclnumIdentificacion(Long clnumIdentificacion){
		return productosRepository.findByClnumIdentificacion(clnumIdentificacion);
	}
	
	@Override
	@Transactional//Listar num cuenta
	public Productos listnumcuenta(Long numCuenta) {
		return productosRepository.getOne(numCuenta);	
	}
	
	@Override
	@Transactional // Modificar estado cuenta
	public Productos cambiarestado(Productos productos) {
		return productosRepository.save(productos);
	}
	
	@Override
	@Transactional // Modificar saldo cuenta
	public Productos actualizarSaldo(Productos productos) {
		return productosRepository.save(productos);
	}
}
