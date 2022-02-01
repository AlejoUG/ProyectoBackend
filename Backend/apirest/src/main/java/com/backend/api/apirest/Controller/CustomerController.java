package com.backend.api.apirest.Controller;  

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

import com.backend.api.apirest.Entity.CustomerEntity;
import com.backend.api.apirest.Entity.ProductEntity;
import com.backend.api.apirest.Service.Impl.CustomerServiceImpl;
import com.backend.api.apirest.Service.Impl.ProductServiceImpl;
import com.backend.api.apirest.dto.GeneralResponse;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@PostMapping //Ingresar un cliente
	public ResponseEntity<GeneralResponse<CustomerEntity>> guardarClientes(@RequestBody CustomerEntity customerEntity) {
		GeneralResponse<CustomerEntity> respuesta = new GeneralResponse<>();
		CustomerEntity data=null;
		System.out.println(customerEntity);
		HttpStatus status = null;
		String msg ="";
		try {
			data=customerServiceImpl.guardarCliente(customerEntity);
			msg = "Cliente creado exitosamente";
			status = HttpStatus.CREATED;
			respuesta.setData(data);
			respuesta.setMensaje(msg);
			respuesta.setSuccess(0);
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(4);
		}
		return new ResponseEntity<>(respuesta,status);
	}

	@GetMapping//Obtener clientes
	public ResponseEntity<GeneralResponse<List<CustomerEntity>>> obtenerClientes() {
		GeneralResponse<List<CustomerEntity>> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		List<CustomerEntity> data = null;
		String msg ="";
		try {
			data = customerServiceImpl.obtenerClientes();
			msg="Clientes obtenidos satisfactoriamente";
			status = HttpStatus.CREATED;
			respuesta.setData(data);
			respuesta.setMensaje(msg);
			respuesta.setSuccess(0);
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(4);
		}
		
		return new ResponseEntity(respuesta, status);
	}
	
	@GetMapping("/{idcliente}") //Obtener un cliente
	public ResponseEntity<GeneralResponse<CustomerEntity>> obtenerCliente(@PathVariable("idcliente") Long idcliente) {
		GeneralResponse<CustomerEntity> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		CustomerEntity data = null;
		String msg ="";
		try {
			if(!customerServiceImpl.clienteExiste(idcliente)) {
				msg = "Cliente no existe";
				status = HttpStatus.NOT_FOUND;
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				return new ResponseEntity(respuesta,status);
			}
			data=customerServiceImpl.obtenerCliente(idcliente);
			msg = "Cliente encontrado";
			status = HttpStatus.OK;
			respuesta.setData(data);
			respuesta.setMensaje(msg);
			respuesta.setSuccess(0);
		} 
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(4);
		}
		return new ResponseEntity(respuesta, status);
	}
	
	@PutMapping("/{idcliente}") //Modificar datos basicos de un cliente
	public ResponseEntity<GeneralResponse<CustomerEntity>> editarCliente(@RequestBody CustomerEntity clientesDetalles, @PathVariable("idcliente") Long idcliente){
		GeneralResponse<CustomerEntity> respuesta = new GeneralResponse<>();
		String msg ="";
		HttpStatus status = null;
		CustomerEntity data = null;	
		System.out.println(clientesDetalles);
		try{
			if(!customerServiceImpl.clienteExiste(idcliente)) {
				msg = "Cliente no existe";
				status = HttpStatus.NOT_FOUND;
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				return new ResponseEntity(respuesta,status);
			}
			data = customerServiceImpl.obtenerCliente(idcliente);
			clientesDetalles.setFechaCreacion(data.getFechaCreacion());
			clientesDetalles.setIdcliente(idcliente);
			customerServiceImpl.modificarCliente(clientesDetalles);
			status = HttpStatus.CREATED;
			msg="Cliente actualizado con exito";
			respuesta.setData(clientesDetalles);
			respuesta.setSuccess(0);
			respuesta.setMensaje(msg);
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;	
			respuesta.setSuccess(4);
			respuesta.setMensaje(msg);
		}
		return new ResponseEntity(respuesta,status);
	}
	
	@DeleteMapping("/{idcliente}") //Eliminar cliente
	public ResponseEntity<GeneralResponse<?>> eliminarClientes(@PathVariable("idcliente") Long idcliente) {
		GeneralResponse<?> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		String msg ="";	
		List<ProductEntity> productEntity = null;
		try {
			if(!customerServiceImpl.clienteExiste(idcliente)) {
				msg = "Cliente no existe";
				status = HttpStatus.NOT_FOUND;
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				return new ResponseEntity(respuesta,status);
			}
			List<ProductEntity> productoCliente = productServiceImpl.listarCuentasCliente(idcliente);
			boolean estadoBandera = existeProductosActivos(productoCliente);
			System.out.println(estadoBandera);
			if(estadoBandera==true) {
				msg = "No se puede eliminar cliente posee productos activos o inactivos";
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				status = HttpStatus.CREATED;
			}
			else if(estadoBandera==false){
				customerServiceImpl.eliminarClientesCancelados(idcliente);
				msg = "Cliente eliminado exitosamente";
				status = HttpStatus.CREATED;
				respuesta.setMensaje(msg);
				respuesta.setSuccess(0);
			}
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;	
			respuesta.setSuccess(4);
			respuesta.setMensaje(msg);
		}
		return new ResponseEntity(respuesta,status);
	}
	
	public boolean existeProductosActivos(List<ProductEntity> productoCliente) {
		boolean estadoBandera = false;
		for (int i = 0; i < productoCliente.size(); i++) {
			if (productoCliente.get(i).getEstado().equals("Activo")
					|| productoCliente.get(i).getEstado().equals("Inactivo")) {
				estadoBandera = true;
				break;
			}
		}
		return estadoBandera;
	}
	
	/*	
	@GetMapping("/clientes/{numCuenta}/estado")
	public List<Clientes> obtenerClientesActivos(@PathVariable("numCuenta") Integer numCuenta, 
			@PathParam("estado") String estado) {
		return clientesService.buscarClientesActivos(numCuenta, estado);
	}*/
	
}
