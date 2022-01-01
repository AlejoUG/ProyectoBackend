package com.backend.Crud.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jca.cci.core.support.CciDaoSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Model.Clientes;

//@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
//@RequestMapping({"/clientesbanco"})
public class ClientesController {
	
	@Autowired
	private ClientesService clientesService;
	
	@GetMapping("Crud/clientes")
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
	
	
	@PostMapping("Crud/clientes")
	public Clientes guardarClientes(@RequestBody Clientes clientes) {
		System.out.println(clientes);
		clientesService.guardar(clientes);
		return clientes;
	}
	
	
	

}
