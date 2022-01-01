package com.backend.api.apirest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.api.apirest.model.Clientes;

@RestController
public class ClientesController {
	
	@Autowired
	private ClientesService clientesService;
	
	@GetMapping("api/clientes")
	public List<Clientes>obtenerClientes(){
		List<Clientes> listaClientes = new ArrayList<>();
		Clientes c = new Clientes();
		c.setNumCuenta(1234);
	    c.setTipCuenta("Debito");
	    c.setNombres("Alvaro"); 
	    c.setApellidos("Torres Blanco");
	    c.setTpIdentificacion("C.C");
	    c.setNumIdentificacion(64852369);     
	    c.setEmail("alvarotB@mail.edu.co");
	    c.setFechaNacimiento("2002-01-30"); 
	    c.setFechaIngreso("2021-12-31");
	    c.setEstado("Activo");
	    c.setSaldo(0);
	    
	    listaClientes.add(c);
		return listaClientes;
	}
	
	@PostMapping("api/clientes")
	public Clientes guardarClientes(@RequestBody Clientes clientes) {
		System.out.println(clientes);
		clientesService.guardar(clientes);
		return clientes;
	}
	
	@GetMapping("api/clientes/{numCuenta}")
	public Clientes obtenerCliente(@PathVariable("numCuenta") Integer numCuenta) {
		return clientesService.obtenerCliente(numCuenta);
	}
	
	@PutMapping("api/clientes")
	public void actualizarCliente(@RequestBody Clientes clientes) {
		clientesService.actualizar(clientes);
	}
	
	@DeleteMapping("api/clientes/{numCuenta}")
	public void eliminar(@PathVariable("numCuenta") Integer numCuenta) {
		clientesService.eliminar(numCuenta);
	}
	
	@GetMapping("api/clientes/{numCuenta}/estado")
	public List<Clientes> obtenerClientesActivos(@PathVariable("numCuenta") Integer numCuenta, 
			@PathParam("estado") String estado) {
		return clientesService.buscarClientesActivos(numCuenta, estado);
	}
	
	@PutMapping("api/clientes/{numCuenta}")
	public void setClientes(@PathParam("nombres") String nombres,@PathParam("apellidos") String apellidos, @PathParam("tpIdentificacion") String tpIdentificacion, 
			@PathParam("numIdentificacion") Integer numIdentificacion, @PathParam("email") String email,@PathParam("fechaNacimiento") String fechaNacimiento, @PathVariable("numCuenta") Integer numCuenta) {
		clientesService.setClientes(nombres, apellidos, tpIdentificacion, numIdentificacion, email, fechaNacimiento, numCuenta);
	}
	
}
