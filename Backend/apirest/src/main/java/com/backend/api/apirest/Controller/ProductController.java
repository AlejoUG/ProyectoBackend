package com.backend.api.apirest.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

import com.backend.api.apirest.Entity.CustomerEntity;
import com.backend.api.apirest.Entity.ProductEntity;
import com.backend.api.apirest.Service.Impl.ProductServiceImpl;
import com.backend.api.apirest.dto.GeneralResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@PostMapping("/accounts") //Crear cuentas	
	public ResponseEntity<GeneralResponse<ProductEntity>> crearCuentas(@RequestBody ProductEntity productos) {
		GeneralResponse<ProductEntity> respuesta = new GeneralResponse<>();
		ProductEntity data=null;
		HttpStatus status = null;
		String msg ="";
		Random random = new Random();
		boolean existe=true;
		System.out.println(productos);
		long x;
		try {
			x=random.nextLong(1999999999);
			while(productServiceImpl.cuentaExiste(x)==existe) {
				x=random.nextLong(1999999999);
			}
			productos.setNumCuenta(x);
			data=productServiceImpl.crearCuentaCliente(productos);
			msg = "Cuenta creada exitosamente";
			status = HttpStatus.CREATED;
			respuesta.setData(data);
			respuesta.setMensaje(msg);
			respuesta.setSuccess(0);
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		return new ResponseEntity(respuesta,status);
	}
	
	@GetMapping("/customers/{clidcliente}/accounts")//Obtener cuentas clientes
	public ResponseEntity<GeneralResponse<List<ProductEntity>>> cuentasCliente(@PathVariable("clidcliente") Long clidcliente) {
		GeneralResponse<List<ProductEntity>> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		List<ProductEntity> productos = null;
		String msg ="";
		boolean bandera=true;
		try {
			productos = productServiceImpl.listarCuentasCliente(clidcliente);
			if(existeCuentas(productos, clidcliente)==bandera) {
				status = HttpStatus.OK;
				msg="Lista de cuentas del cliente";
				respuesta.setData(productos);
				respuesta.setMensaje(msg);
				respuesta.setSuccess(0);
			}
			else {
				msg = "Cliente no posee cuentas";
				status = HttpStatus.NOT_FOUND;
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				return new ResponseEntity(respuesta,status);
			}
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		return new ResponseEntity(respuesta,status);
	}

	@GetMapping("/customers/{clidcliente}/accounts/{numCuenta}")//obtener cuenta
	public ResponseEntity<GeneralResponse<ProductEntity>> listarProducto(@PathVariable("numCuenta") Long numCuenta,
			@PathVariable("clidcliente") Long clidcliente) {
		GeneralResponse<ProductEntity> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		ProductEntity productos = null;
		String msg ="";
		try {
			productos=productServiceImpl.listnumcuenta(numCuenta);
			status = HttpStatus.OK;
			msg = "Cuenta seleccionada";
			respuesta.setMensaje(msg);
			respuesta.setSuccess(0);
			respuesta.setData(productos);
		} 
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);		
		}
		return new ResponseEntity(respuesta,status);
	}
	
	@PutMapping("/accounts/{numCuenta}/activate") //Activar cuenta de un cliente
	public ResponseEntity<GeneralResponse<ProductEntity>>  activar(ProductEntity productos, @PathVariable("numCuenta") Long numCuenta){
		GeneralResponse<ProductEntity> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		String msg ="";
		try {
			productos=productServiceImpl.listnumcuenta(numCuenta);
			if(productos.getEstado().equals("Activo")) {
				msg = "Cuenta ya se encuentra activa";
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				return new ResponseEntity(respuesta,HttpStatus.CONFLICT);
			}
			else if(productos.getEstado().equals("Cancelado")) {
				msg = "Cuenta se encuentra cancelada no se puede activar o inactivar";
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				return new ResponseEntity(respuesta,HttpStatus.CONFLICT);
			}
			else {
				productos=cambiarEstadoCuenta(productos);
				msg = "Cuenta activada exitosamente";
				status = HttpStatus.OK;
				respuesta.setData(productos);
				respuesta.setMensaje(msg);
				respuesta.setSuccess(3);
				}
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		return new ResponseEntity(respuesta,status);
	}
	
	@PutMapping("/accounts/{numCuenta}/inactivate") //Inactivar cuenta de un cliente
	public ResponseEntity<GeneralResponse<ProductEntity>>  inactivar(ProductEntity productos, @PathVariable("numCuenta") Long numCuenta){
		GeneralResponse<ProductEntity> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		String msg ="";
		try {
			productos=productServiceImpl.listnumcuenta(numCuenta);
			if(productos.getEstado().equals("Inactivo")) {
				msg = "Cuenta ya se encuentra inactiva";
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				return new ResponseEntity(respuesta,HttpStatus.CONFLICT);
			}
			else if(productos.getEstado().equals("Cancelado")) {
				msg = "Cuenta se encuentra cancelada no se puede activar o inactivar";
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				return new ResponseEntity(respuesta,HttpStatus.CONFLICT);
			}
			else{
				productos=cambiarEstadoCuenta(productos);			
				msg = "Cuenta inactivada exitosamente";
				status = HttpStatus.OK;
				respuesta.setData(productos);
				respuesta.setMensaje(msg);
				respuesta.setSuccess(3);}
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		return new ResponseEntity(respuesta,status);
	}
	
	@PutMapping("/accounts/{numCuenta}/cancel") //cancelar cuenta de un cliente
	public ResponseEntity<GeneralResponse<ProductEntity>>  cancelar(ProductEntity productos, @PathVariable("numCuenta") Long numCuenta){
		int estado=0;
		GeneralResponse<ProductEntity> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		String msg ="";
		try {
			productos=productServiceImpl.listnumcuenta(numCuenta);
			estado=saberSiCuentaSePuedeCancelar(productos);
			if(estado==1) {
				productos.setEstado("Cancelado");
				productos=productServiceImpl.cambiarestado(productos);
				msg = "Cuenta cancelada exitosamente";
				status = HttpStatus.OK;
				respuesta.setData(productos);
				respuesta.setMensaje(msg);
				respuesta.setSuccess(3);
			}
			else if(estado==2) {
				productos.setEstado(productos.getEstado());
				productos=productServiceImpl.cambiarestado(productos);
				msg = "La cuenta ya se encuentra cancelada";
				status = HttpStatus.CONFLICT;
				respuesta.setData(productos);
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				}
			else {
				productos.setEstado(productos.getEstado());
				productos=productServiceImpl.cambiarestado(productos);
				msg = "No se puede cancelar posee saldo en la cuenta";
				status = HttpStatus.CONFLICT;
				respuesta.setData(productos);
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
			}
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		return new ResponseEntity(respuesta,status);
	}
	
	public ProductEntity cambiarEstadoCuenta(ProductEntity productos){
		if(productos.getEstado().equals("Activo")) {
			productos.setEstado("Inactivo");
		}
		else if(productos.getEstado().equals("Inactivo")) {
			productos.setEstado("Activo");
		}
		else if(productos.getEstado().equals("Cancelado")) {
			productos.setEstado("Cancelado");
		}
		//productServiceImpl.cambiarestado(productos);
		return productServiceImpl.cambiarestado(productos);
	}
	
	public Integer saberSiCuentaSePuedeCancelar(ProductEntity productos) {
		int estadoBandera = 0;
		if(productos.getSaldo()==0 && (productos.getEstado().equals("Activo")||productos.getEstado().equals("Inactivo"))) {
			return 1;
		}
		else if(productos.getEstado().equals("Cancelado")) {
			return 2;
		}
		return estadoBandera;
	}
	
	public boolean existeCuentas(List<ProductEntity> productoCliente, Long clidcliente) {
		boolean estadoBandera = false;
		for (int i = 0; i < productoCliente.size(); i++) {
			if (productoCliente.get(i).getClidcliente().equals(clidcliente)) {
				estadoBandera = true;
				break;
			}
		}
		return estadoBandera;
	}
	
	/*//Actualizar saldo
	public ResponseEntity<GeneralResponse<ProductEntity>>  actualizarSaldo(ProductEntity productos, Long numCuenta, Double saldo){
		GeneralResponse<ProductEntity> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		String msg ="";
		try {
			productos=productServiceImpl.listnumcuenta(numCuenta);
			productos.setSaldo(saldo);
			productServiceImpl.actualizarSaldo(productos);
			msg="Saldo actualizado correctamente";
			status = HttpStatus.OK;
			respuesta.setData(productos);
			respuesta.setMensaje(msg);
			respuesta.setSuccess(false);
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con suporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(false);
		}
		return new ResponseEntity(productos,status);
	}*/
	
	//@PutMapping("/estado/{numCuenta}") //Modificar estado cuenta de un cliente
	/*
	//Actualizar saldo
		public ProductEntity actualizarSaldo(ProductEntity productos, Long numCuenta, Double saldo){
			//productos = productServiceImpl.getOne(numCuenta);
			productos.setSaldo(saldo);
			//ProductEntity data=productServiceImpl.actualizarSaldo(productos);
			return productos;
		}
	
	/*@PutMapping("/cancelar/{numCuenta}") //cancelar cuenta de un cliente
	public ProductEntity cancelar(ProductEntity productos, @PathVariable("numCuenta") Long numCuenta){
		productos = obtenerProducto(numCuenta);
		if(productos.getSaldo()==0 && (productos.getEstado().equals("Activo")||productos.getEstado().equals("Inactivo"))) {
			productos.setEstado("Cancelado");
		}else {productos.setEstado(productos.getEstado());}
		
		return productServiceImpl.cambiarestado(productos);
	}
	
	@PutMapping("/estado/{numCuenta}") //Modificar estado cuenta de un cliente
	public ProductEntity cambiarestado(ProductEntity productos, @PathVariable("numCuenta") Long numCuenta){
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
		return productServiceImpl.cambiarestado(productos);
	}
	
	public String nosaldo(String var){
		return "No hay saldo suficiente para realizar la operacion";
	}
	public String cuentaCancelada(String var){
		return "Cuenta o producto cancelado no puede hacer movimientos";
	}
	public String cuentaInactivaNoMovimiento(String var){
		return "No puede retirar o transferir solo consignar a su cuenta";
	}*/
	
}
