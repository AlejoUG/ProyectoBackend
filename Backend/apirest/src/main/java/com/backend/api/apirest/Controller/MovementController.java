package com.backend.api.apirest.Controller;

import java.util.List;

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
import com.backend.api.apirest.Entity.MovementEntity;
import com.backend.api.apirest.Entity.ProductEntity;
import com.backend.api.apirest.Service.Impl.MovementServiceImpl;
import com.backend.api.apirest.Service.Impl.ProductServiceImpl;
import com.backend.api.apirest.dto.GeneralResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
public class MovementController {
	
	@Autowired
	private MovementServiceImpl movementServiceImpl;
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping("customers/{idcliente}/accounts/{pronumCuenta}/movimientos") //Obtener movimientos cuenta clientes
	public ResponseEntity<GeneralResponse<List<MovementEntity>>> buscarMovimientosClientes(@PathVariable("pronumCuenta") Long pronumCuenta) {
		GeneralResponse<List<MovementEntity>> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		List<MovementEntity> data = null;
		String msg ="";
		try {
			System.out.println(data);
			data = movementServiceImpl.buscarMovimientosClientes(pronumCuenta);
			System.out.println(data);
			System.out.println(!movementServiceImpl.cuenaExiste(pronumCuenta));
			if (!movementServiceImpl.cuenaExiste(pronumCuenta)) {
				msg="La cuenta no posee movimientos realizados";
				status = HttpStatus.NOT_FOUND;
				respuesta.setData(data);
				respuesta.setMensaje(msg);
				respuesta.setSuccess(1);
				return new ResponseEntity(respuesta, status);
			}
			msg="Movimientos obtenidos satisfactoriamente";
			status = HttpStatus.CREATED;
			respuesta.setData(data);
			respuesta.setMensaje(msg);
			respuesta.setSuccess(0);
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con soporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		
		return new ResponseEntity(respuesta, status);
	}
	
	@PutMapping("accounts/{pronumCuenta}/add") //Hacer consignacion cuentas
	public ResponseEntity<GeneralResponse<MovementEntity>> consignarMovimientos(@PathVariable("pronumCuenta") Long pronumCuenta,@RequestBody MovementEntity movimientos) {
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		String msg ="";
		boolean estadoCancelada=true;
		try {
			System.out.println(movimientos); // Imprime datos obtenidos por el RequestBody
			ProductEntity productos = productServiceImpl.listnumcuenta(pronumCuenta); // Para obtener datos de la cuenta que desea hacer el movimiento
			movimientos.setPronumCuenta(pronumCuenta); // Para asociar el movimiento con la cuenta
			if(cuentaEstaCancelada(productos)==estadoCancelada) {
				return cuentaCancelada();
			}
			else if(movimientos.getTpMovimiento().equals("Consignacion")) {
				adicion(productos, movimientos);				
				msg = "Consignacion realizada exitosamente";
				status = HttpStatus.CONFLICT;
				respuesta.setMensaje(msg);
				respuesta.setSuccess(0);
				respuesta.setData(movimientos);
			}
			status= HttpStatus.CONFLICT;
			respuesta.setSuccess(1);
			respuesta.setMensaje("Operacion invalida no es un retiro o Transferencia");
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con soporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		return new ResponseEntity(respuesta, status);
	}
	
	@PutMapping("accounts/{pronumCuenta}/withdrawal") //Hacer consignacion cuentas
	public ResponseEntity<GeneralResponse<MovementEntity>> retirarMovimientos(@PathVariable("pronumCuenta") Long pronumCuenta,@RequestBody MovementEntity movimientos) {
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		String msg ="";
		boolean estadoCancelada=true;
		boolean estadoInactiva=true;
		boolean estadoConsultarSaldo=false;
		try {
			System.out.println(movimientos); // Imprime datos obtenidos por el RequestBody
			ProductEntity productos = productServiceImpl.listnumcuenta(pronumCuenta); // Para obtener datos de la cuenta que desea hacer el movimiento
			movimientos.setPronumCuenta(pronumCuenta); // Para asociar el movimiento con la cuenta
			if(cuentaEstaCancelada(productos)==estadoCancelada) {
				return cuentaCancelada();
			}
			else if(cuentaEstaInactiva(productos)==estadoInactiva) {
				return cuentaInactiva();
			}
			
			else if(consultarSaldo(productos.getSaldo(), movimientos.getMonto(),productos)==estadoConsultarSaldo) {
				return noHaySaldo();
			}
			else if(productos.getTipCuenta().equals("Ahorro") && movimientos.getTpMovimiento().equals("Retiro")) {
				sustraccion(productos, movimientos);
				return smsRetiro(movimientos);
			}
			else if(productos.getTipCuenta().equals("Corriente") && movimientos.getTpMovimiento().equals("Retiro")){
				sustraccion(productos, movimientos);
				return smsRetiro(movimientos);
			}			
			status= HttpStatus.CONFLICT;
			msg="Operacion invalida no es una consignacion o transferencia";
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con soporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		return new ResponseEntity(respuesta, status);
	}
	
	@PutMapping("accounts/{pronumCuenta}/transfer") //Hacer tranferencia cuentas
	public ResponseEntity<GeneralResponse<MovementEntity>> transferenciaMovimientos(@PathVariable("pronumCuenta") Long pronumCuenta,@RequestBody MovementEntity movimientos) {
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		HttpStatus status = null;
		String msg ="";
		boolean estadoCancelada=true;
		boolean estadoInactiva=true;
		boolean estadoConsultarSaldo=false;
		try {
			System.out.println(movimientos); // Imprime datos obtenidos por el RequestBody
			ProductEntity productos = productServiceImpl.listnumcuenta(pronumCuenta); // Para obtener datos de la cuenta que desea hacer el movimiento
			movimientos.setPronumCuenta(pronumCuenta); // Para asociar el movimiento con la cuenta
			
			ProductEntity productosrecibir = productServiceImpl.listnumcuenta(movimientos.getPronumCuenta2());//Obtener datos de cuenta que recibe la transferencia
			MovementEntity movimientos1 = new MovementEntity();//Para hacer el movimiento en la cuenta que recibe se instancia
			System.out.println(productosrecibir); // Imprime datos consultados de la cuenta que va a recibir la transferencia
			
			if(cuentaEstaCancelada(productos)==estadoCancelada) {
				return cuentaCancelada();
			}
			else if(cuentaEstaInactiva(productos)==estadoInactiva) {
				return cuentaInactiva();
			}
			
			else if(consultarSaldo(productos.getSaldo(), movimientos.getMonto(),productos)==estadoConsultarSaldo) {
				return noHaySaldo();
			}
			else if(productos.getTipCuenta().equals("Ahorro") && movimientos.getTpMovimiento().equals("Transferencia")) {
				if(cuentaEstaCancelada(productosrecibir)==estadoCancelada) {
					return cuentaRecibeCancelada();
				}
				else if(cuentaEstaInactiva(productosrecibir)==estadoInactiva) {
					return cuentaRecibeInactiva();
				}
				sustraccion(productos, movimientos);
				movimientos1=movimientoQuienRecibe(movimientos, pronumCuenta);
				adicion(productosrecibir, movimientos1);
				return smsTransferencia(movimientos);
			}
			else if(productos.getTipCuenta().equals("Corriente") && movimientos.getTpMovimiento().equals("Transferencia")){
				if(cuentaEstaCancelada(productosrecibir)==estadoCancelada) {
					return cuentaRecibeCancelada();
				}
				else if(cuentaEstaInactiva(productosrecibir)==estadoInactiva) {
					return cuentaRecibeInactiva();
				}
				sustraccion(productos, movimientos);
				movimientos1=movimientoQuienRecibe(movimientos, pronumCuenta);
				adicion(productosrecibir, movimientos1);
				return smsTransferencia(movimientos);
			}			
			status= HttpStatus.CONFLICT;
			msg="Operacion invalida no es una consignacion o transferencia";
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		catch(Exception e) {
			msg = "Algo ah fallado. Contactar con soporte";
			status = HttpStatus.NOT_IMPLEMENTED;
			respuesta.setMensaje(msg);
			respuesta.setSuccess(1);
		}
		return new ResponseEntity(respuesta, status);
	}
	
	public boolean cuentaEstaCancelada (ProductEntity productos) {
		//Para cancelar movimiento de  retiro cuenta cancelada que desea hacer el movimiento
		if(productos.getEstado().equals("Cancelado")) {
			return true; //Cuenta o producto cancelado no puede hacer movimientos;
		}
		return false;
	}
	
	public boolean cuentaEstaInactiva (ProductEntity productos) {
		//Para cancelar movimiento de  retiro cuenta inactiva que desea hacer el movimiento
		if(productos.getEstado().equals("Inactivo")) {
			return true; //No puede retirar solo consignar a su cuenta
		}	
		//Para cancelar movimiento de transferencia cuenta inactiva que desea hacer el movimiento
		else if(productos.getEstado().equals("Inactivo")) {
			return true; //No puede transferir solo consignar a su cuenta
		}
		return false;
	}
	
	public boolean consultarSaldo(Double saldo, Double monto, ProductEntity productos) {
		// Para retirar de la cuenta ahorro
		if (productos.getTipCuenta().equals("Ahorro") && (saldo-(1.004*monto)) >= 0) {
			return true;
			}
	    // Para retirar de la cuenta corriente
		else if (productos.getTipCuenta().equals("Corriente") && (saldo-(1.004*monto)) >= -2000000) {
			return true;
			}
		return false;
		}
	
	public ResponseEntity<GeneralResponse<MovementEntity>> cuentaCancelada(){
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		String msg = "Cuenta cancelada no puede realizar movimientos bancarios";
		respuesta.setMensaje(msg);
		respuesta.setSuccess(1);
		return new ResponseEntity(respuesta, HttpStatus.CONFLICT);
	}
	
	public ResponseEntity<GeneralResponse<MovementEntity>> cuentaRecibeCancelada(){
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		String msg = "La Cuenta de destino se encuentra cancelada no puede recibir movimientos bancarios";
		respuesta.setMensaje(msg);
		respuesta.setSuccess(1);
		return new ResponseEntity(respuesta, HttpStatus.CONFLICT);
	}
	
	public ResponseEntity<GeneralResponse<MovementEntity>> cuentaInactiva(){
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		String msg = "Cuenta inactiva no puede realizar transacciones o retiro";
		respuesta.setMensaje(msg);
		respuesta.setSuccess(1);
		return new ResponseEntity(respuesta, HttpStatus.CONFLICT);
	}	
	
	public ResponseEntity<GeneralResponse<MovementEntity>> cuentaRecibeInactiva(){
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		String msg = "La Cuenta que envio la transaccion se encuentra inactiva no puede recibir movimientos bancarios";
		respuesta.setMensaje(msg);
		respuesta.setSuccess(1);
		return new ResponseEntity(respuesta, HttpStatus.CONFLICT);
	}
	
	public ResponseEntity<GeneralResponse<MovementEntity>> noHaySaldo(){
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		String msg = "Fondos insuficientes";
		respuesta.setMensaje(msg);
		respuesta.setSuccess(2);
		return new ResponseEntity(respuesta, HttpStatus.CONFLICT);
	}
	
	public ResponseEntity<GeneralResponse<MovementEntity>> smsRetiro(MovementEntity movimientos){
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		String msg = "Retiro realizado exitosamente";
		respuesta.setData(movimientos);
		respuesta.setMensaje(msg);
		respuesta.setSuccess(0);
		return new ResponseEntity(respuesta, HttpStatus.OK);
	}
	public ResponseEntity<GeneralResponse<MovementEntity>> smsTransferencia(MovementEntity movimientos){
		GeneralResponse<MovementEntity> respuesta = new GeneralResponse<>();
		String msg = "Transferencia realizada exitosamente";
		respuesta.setData(movimientos);
		respuesta.setMensaje(msg);
		respuesta.setSuccess(0);
		return new ResponseEntity(respuesta, HttpStatus.OK);
	}
	
	public void sustraccion(ProductEntity productos, MovementEntity movimientos) {
		try {
			productos.setSaldo(productos.getSaldo()-movimientos.getMonto());
			productServiceImpl.actualizarSaldo(productos);
			movimientos.setGmf(movimientos.getMonto()*0.004);
			movimientos=movementServiceImpl.guardarMovimiento(movimientos);
		}
		catch(Exception e) {
			
		}
	}
	
	public void adicion(ProductEntity productos, MovementEntity movimientos) {
		try {
			productos.setSaldo(productos.getSaldo()+movimientos.getMonto());
			productServiceImpl.actualizarSaldo(productos);
			movimientos=movementServiceImpl.guardarMovimiento(movimientos);
		}
		catch(Exception e) {
			
		}
	}
	public MovementEntity movimientoQuienRecibe(MovementEntity movimientos, Long pronumCuenta){
		MovementEntity movimientos1 = new MovementEntity();
		movimientos1.setDescripcion("Recibio de: "+pronumCuenta+" un monto de "+ movimientos.getMonto());
		movimientos1.setFechaMovimiento(movimientos.getFechaMovimiento());
		movimientos1.setMonto(movimientos.getMonto());
		movimientos1.setPronumCuenta(movimientos.getPronumCuenta2());
		movimientos1.setPronumCuenta2(pronumCuenta);
		movimientos1.setTpMovimiento(movimientos.getTpMovimiento());
		return movimientos1;
	}
	
	/*@PutMapping("/{pronumCuenta}/2") //Hacer movimientos clientes
	public MovementEntity retirarMovimientos(@PathVariable("pronumCuenta") Long pronumCuenta,@RequestBody MovementEntity movimientos) {
		// Imprime datos obtenidos por el RequestBody
		System.out.println(movimientos);
		// Para obtener datos de la cuenta que desea hacer el movimiento
		ProductEntity productos = productServiceImpl.listnumcuenta(pronumCuenta);
		// Para asociar rl movimiento con la cuenta
		movimientos.setPronumCuenta(pronumCuenta);

		// Para saber si la cuenta esta cancelada que hacer.
		if (productos.getEstado().equals("Cancelado")) {
			return null; // Cuenta o producto cancelado no puede hacer movimientos;
		}

		// Para cancelar movimiento de retiro cuenta inactiva que desea hacer el
		// movimiento
		else if (movimientos.getTpMovimiento().equals("Retiro") && productos.getEstado().equals("Inactivo")) {
			return null; // No puede retirar solo consignar a su cuenta
		}

		// Para cancelar movimiento de transferencia cuenta inactiva que desea hacer el
		// movimiento
		else if (productos.getEstado().equals("Inactivo") && movimientos.getTpMovimiento().equals("Transferencia")) {
			return null; // No puede transferir solo consignar a su cuenta
		}

		// Para hacer retiro cuenta
		else if (movimientos.getTpMovimiento().equals("Retiro") && productos.getEstado().equals("Activo")) {

			// Para retirar de la cuenta de ahorro
			if (productos.getTipCuenta().equals("Ahorro") && (productos.getSaldo() - movimientos.getMonto()) >= 0) {
				productos.setSaldo(productos.getSaldo() - movimientos.getMonto());
				productServiceImpl.actualizarSaldo(productos);
				movementServiceImpl.guardar(movimientos);
				return movimientos;
			}

			// Para retirar de la cuenta corriente
			else if (productos.getTipCuenta().equals("Corriente")
					&& (productos.getSaldo() - movimientos.getMonto()) >= -2000000) {
				productos.setSaldo(productos.getSaldo() - movimientos.getMonto());
				productServiceImpl.actualizarSaldo(productos);
				movementServiceImpl.guardar(movimientos);
				return movimientos;
			}

			// Si no hay saldo suficiente en la cuenta
			else {
				return null;
			} // Saldo insuficiente verifique el monto que puede retirar
		}

		return null;
	}
	
	@PutMapping("/{pronumCuenta}") //Hacer movimientos clientes
	public MovementEntity tranferenciaMovimientos(@PathVariable("pronumCuenta") Long pronumCuenta,@RequestBody MovementEntity movimientos) {
		//Imprime datos obtenidos por el RequestBody
		System.out.println(movimientos);
		//Para obtener datos de la cuenta que desea hacer el movimiento
		ProductEntity productos = productServiceImpl.listnumcuenta(pronumCuenta);
		//Para asociar rl movimiento con la cuenta
		movimientos.setPronumCuenta(pronumCuenta);
		
		//Para saber si la cuenta esta cancelada que hacer.
		if(productos.getEstado().equals("Cancelado")) {
			return null; //Cuenta o producto cancelado no puede hacer movimientos;
		}
		
		//Para cancelar movimiento de  retiro cuenta inactiva que desea hacer el movimiento
		else if(movimientos.getTpMovimiento().equals("Retiro") && productos.getEstado().equals("Inactivo")) {
			return null; //No puede retirar solo consignar a su cuenta
		}
		
		//Para cancelar movimiento de transferencia cuenta inactiva que desea hacer el movimiento
		else if(productos.getEstado().equals("Inactivo") && movimientos.getTpMovimiento().equals("Transferencia")) {
			return null; //No puede transferir solo consignar a su cuenta
		}
		
		//Para hacer transferencia entre cuentas.
		else if(productos.getEstado().equals("Activo") && movimientos.getTpMovimiento().equals("Transferencia")) {
			
			//Obtener datos de cuenta que recibe la transferencia
			ProductEntity productosrecibir = productServiceImpl.listnumcuenta(movimientos.getPronumCuenta2());
			System.out.println(productosrecibir);
			//Para hacer el movimiento en la cuenta que recibe se instancia
			MovementEntity movimientos1 = new MovementEntity();
			
			//confirmar que la cuenta que envia es de ahooro si tiene saldo para enviar y si la cuenta que recibe esta activa
			if(productos.getTipCuenta().equals("Ahorro") && (productos.getSaldo()-movimientos.getMonto())>=0 && productosrecibir.getEstado().equals("Activo")) {
				//Registra movimiento y actualioza saldo quien transfiere
				productos.setSaldo(productos.getSaldo()-movimientos.getMonto());
				productServiceImpl.actualizarSaldo(productos);
				movementServiceImpl.guardar(movimientos);
				//Para registrar movimiento quien recibe la transferencia 
				movimientos1.setDescripcion("Recibio");
				movimientos1.setFechaMovimiento(movimientos.getFechaMovimiento());
				movimientos1.setMonto(movimientos.getMonto());
				movimientos1.setPronumCuenta(movimientos.getPronumCuenta2());
				movimientos1.setPronumCuenta2(pronumCuenta);
				movimientos1.setTpMovimiento(movimientos.getTpMovimiento());
				movementServiceImpl.guardar(movimientos1);
				//Para actualizar saldo quien recibe la transferencia
				productosrecibir.setSaldo(productosrecibir.getSaldo()+movimientos.getMonto());
				productServiceImpl.actualizarSaldo(productosrecibir);
				return movimientos;	
			}
			//Confirmar que la cuenta que envia es corriente si tiene saldo para enviar y si la cuenta que recibe esta activa
			else if(productos.getTipCuenta().equals("Corriente") && (productos.getSaldo()-movimientos.getMonto())>=-2000000 && productosrecibir.getEstado().equals("Activo")) {
				//Registra movimiento y actualioza saldo quien transfiere
				productos.setSaldo(productos.getSaldo()-movimientos.getMonto());
				productServiceImpl.actualizarSaldo(productos);
				movementServiceImpl.guardar(movimientos);
				//Para registrar movimiento quien recibe la transferencia
				movimientos1.setDescripcion("Recibio");
				movimientos1.setFechaMovimiento(movimientos.getFechaMovimiento());
				movimientos1.setMonto(movimientos.getMonto());
				movimientos1.setPronumCuenta(movimientos.getPronumCuenta2());
				movimientos1.setPronumCuenta2(pronumCuenta);
				movimientos1.setTpMovimiento(movimientos.getTpMovimiento());
				movementServiceImpl.guardar(movimientos1);
				//Para actualizar saldo quien recibe la transferencia
				productosrecibir.setSaldo(productosrecibir.getSaldo()+movimientos.getMonto());
				productServiceImpl.actualizarSaldo(productosrecibir);
				return movimientos;	
			}
			//Si no hay saldo suficiente en la cuenta para transferir
			else {return null;}// No posee saldo suficiente para realizar el movimiento
		}
			
		return movimientos;
	}*/
}
