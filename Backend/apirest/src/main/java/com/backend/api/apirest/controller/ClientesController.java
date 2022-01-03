package com.backend.api.apirest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.api.apirest.model.Clientes;

@RestController
@RequestMapping("api/clientes")
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
public class ClientesController {
	
	@Autowired
	private ClientesService clientesService;
	
	@PostMapping
	public Clientes guardarClientes(@RequestBody Clientes clientes) {
		System.out.println(clientes);
		clientesService.guardar(clientes);
		return clientes;
	}
	
	@GetMapping("/clientes/{numCuenta}")
	public Clientes obtenerCliente(@PathVariable("numCuenta") Integer numCuenta) {
		return clientesService.obtenerCliente(numCuenta);
	}
	
	@PutMapping("/clientes/{numCuenta}")
	public ResponseEntity<?> actualizarCliente(@RequestBody Clientes clientesDetalles, @PathVariable("numCuenta") Integer numCuenta){
		Optional<Clientes> clientes = clientesService.findByNumCuenta(numCuenta);
		
		if(!clientes.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		clientes.get().setNombres(clientesDetalles.getNombres());
		clientes.get().setApellidos(clientesDetalles.getApellidos());
		clientes.get().setTpIdentificacion(clientesDetalles.getTpIdentificacion());
		clientes.get().setNumIdentificacion(clientesDetalles.getNumIdentificacion());
		clientes.get().setEmail(clientesDetalles.getEmail());
		clientes.get().setFechaNacimiento(clientesDetalles.getFechaNacimiento());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.save(clientes.get()));
	}
			
	@DeleteMapping("/clientes/{numCuenta}")
	public void eliminar(@PathVariable("numCuenta") Integer numCuenta) {
		clientesService.eliminar(numCuenta);
	}
	
	@GetMapping("/clientes/{numCuenta}/estado")
	public List<Clientes> obtenerClientesActivos(@PathVariable("numCuenta") Integer numCuenta, 
			@PathParam("estado") String estado) {
		return clientesService.buscarClientesActivos(numCuenta, estado);
	}
	
}
