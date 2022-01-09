package com.backend.api.apirest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.api.apirest.model.Clientes;
import com.backend.api.apirest.model.Productos;
import com.backend.api.apirest.repository.ProductosRepository;

@Service
public class ProductosService {
	
	@Autowired
	private ProductosRepository productosRepository;
	
	//Guardar producto
	public void guardar(Productos productos){
		productosRepository.save(productos);
	}
	
	//Listar productos
	public List<Productos> findAll(){
		return productosRepository.findAll();
	}
	
	//Listar producto
	public Productos obtenerProducto(Long numCuenta) {
		return productosRepository.getOne(numCuenta);	
	}
	
	//Pasar de activo a inactivo
	@Transactional
	public void pasarActivoInactivo(Long numCuenta){
		productosRepository.pasarActivoInactivo(numCuenta);
	}
	
	//Pasar de inactivo a activo
	@Transactional
	public void pasarInactivoActivo(Long numCuenta){
		productosRepository.pasarInactivoActivo(numCuenta);
	}
	
	//Pasar a cancelado
	@Transactional
	public void cancelarCuenta(Long numCuenta){
		productosRepository.cancelarCuenta(numCuenta);
	}
	
	/*public Productos cambiarEstadoCancelado(Productos productos) {
        return productosRepository.save(productos);
    }
	*/	
}
