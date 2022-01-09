package com.backend.api.apirest.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/{numIdentificacion}")
	public Productos guardarMovimientos(@PathVariable("numIdentificacion") Long numIdentificacion , @RequestBody Productos productos) {
		System.out.println(productos);
		productosService.guardar(productos);
		return productos;
	}
	
	@GetMapping
	public List<Productos> obtenerProductos() {
		return productosService.findAll();
	}
	
	@GetMapping("/{numCuenta}")
	public Productos obtenerProducto(@PathVariable("numCuenta") Long numCuenta) {
		return productosService.obtenerProducto(numCuenta);
	}
	
	@PutMapping("/activo/{numCuenta}")
	public void  pasarActivoInactivo(@PathVariable("numCuenta") Long numCuenta) {
		productosService.pasarActivoInactivo(numCuenta);
	}
	
	@PutMapping("/inactivo/{numCuenta}")
	public void pasarInactivoActivo(@PathVariable("numCuenta") Long numCuenta) {
		productosService.pasarInactivoActivo(numCuenta);
	}
	
	@PutMapping("/cancelar/{numCuenta}")
	public void pasarCancelado(@PathVariable("numCuenta") Long numCuenta) {
		productosService.cancelarCuenta(numCuenta);
	}
	
	/*@PutMapping("/{idProduct}/cancel")
    public Productos cancelProduct(Productos productos, @PathVariable("numCuenta") Long numCuenta){
        productos = obtenerProducto(numCuenta);
        productos.setNumCuenta(numCuenta);
        productos.setCl_numIdentificacion(productos.getCl_numIdentificacion());
        productos.setTipCuenta(productos.getTipCuenta());
        productos.setEstado(productos.getEstado());
        productos.setFechaApertura(productos.getFechaApertura());
        productos.setSaldo(productos.getSaldo());
        if (productos.getSaldo()!=0)
        {
            productos.setEstado(productos.getEstado());
        }
        else{
        	productos.setEstado("Cancelado");
        }
        return productosService.cambiarEstadoCancelado(productos);
    }*/
	
}
