package com.backend.api.apirest.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.api.apirest.model.Clientes;
import com.backend.api.apirest.model.Productos;
import com.backend.api.apirest.service.ProductosService;

@RestController
@RequestMapping("api/clientes/productos")
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
public class ProductosController {

	@Autowired
	private ProductosService productosService;
	
	@PostMapping("/{numIdentificacion}") //Crear cuentas	
	public Productos crearCuentas(@PathVariable("numIdentificacion") Long numIdentificacion , @RequestBody Productos productos) {
		System.out.println(productos);
		productosService.guardar(productos);
		return productos;
	}
	
	@GetMapping("/cuentas/{clnumIdentificacion}")//Obtener cuentas clientes
	public List<Productos> findByCl_numIdentificacion(@PathVariable("clnumIdentificacion") Long clnumIdentificacion) {
		return productosService.listclnumIdentificacion(clnumIdentificacion);
	}
	
	@GetMapping("/cuenta/{numCuenta}")//obtener cuenta
	public Productos obtenerProducto(@PathVariable("numCuenta") Long numCuenta) {
		return productosService.listnumcuenta(numCuenta);
	}
	
	@PutMapping("/cancelar/{numCuenta}") //cancelar cuenta de un cliente
	public Productos cancelar(Productos productos, @PathVariable("numCuenta") Long numCuenta){
		productos = obtenerProducto(numCuenta);
		if(productos.getSaldo()==0 && (productos.getEstado().equals("Activo")||productos.getEstado().equals("Inactivo"))) {
			productos.setEstado("Cancelado");
		}else {productos.setEstado(productos.getEstado());}
		
		return productosService.cambiarestado(productos);
	}
	
	@PutMapping("/estado/{numCuenta}") //Modificar estado cuenta de un cliente
	public Productos cambiarestado(Productos productos, @PathVariable("numCuenta") Long numCuenta){
		productos = obtenerProducto(numCuenta);
		if(productos.getEstado().equals("Activo")) {
			productos.setEstado("Inactivo");
		}
		else if(productos.getEstado().equals("Inactivo")) {
			productos.setEstado("Activo");
		}
		else if(productos.getEstado().equals("Cancelado")) {
			productos.setEstado("Cancelado");
		}
		return productosService.cambiarestado(productos);
	}
	
	//Actualizar saldo
	public Productos actualizarSaldo(Productos productos, @PathVariable("numCuenta") Long numCuenta, Double saldo){
		productos = obtenerProducto(numCuenta);
		productos.setSaldo(saldo);
		return productosService.actualizarSaldo(productos);
	}
}
