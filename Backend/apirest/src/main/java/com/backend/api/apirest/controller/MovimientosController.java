package com.backend.api.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.api.apirest.model.Clientes;
import com.backend.api.apirest.model.Movimientos;
import com.backend.api.apirest.model.Productos;
import com.backend.api.apirest.service.MovimientosService;
import com.backend.api.apirest.service.ProductosService;

@RestController
@RequestMapping("api/clientes/movimientos")
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
public class MovimientosController {
	
	@Autowired
	private MovimientosService movimientosService;
	
	@Autowired
	private ProductosService productosService;
	
	@GetMapping("/{pronumCuenta}") //Obtener movimientos cuenta clientes
	public List<Movimientos> buscarMovimientosClientes(@PathVariable("pronumCuenta") Long pronumCuenta) {
		return movimientosService.buscarMovimientosClientes(pronumCuenta);
	}
	
	@PostMapping("/{pronumCuenta}") //Hacer movimientos clientes
	public Movimientos guardarMovimientos(@PathVariable("pronumCuenta") Long pronumCuenta,@RequestBody Movimientos movimientos) {
		//Imprime datos obtenidos por el RequestBody
		System.out.println(movimientos);
		//Para obtener datos de la cuenta que desea hacer el movimiento
		Productos productos = productosService.listnumcuenta(pronumCuenta);
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
		
		//Para hacer consignacion cuenta
		else if(movimientos.getTpMovimiento().equals("Consignacion")) {
			productos.setSaldo(productos.getSaldo()+movimientos.getMonto());
			productosService.actualizarSaldo(productos);
			movimientosService.guardar(movimientos);
			return movimientos;
		}
		
		//Para hacer retiro cuenta
		else if(movimientos.getTpMovimiento().equals("Retiro") && productos.getEstado().equals("Activo")) {
			
			//Para retirar de la cuenta de ahorro
			if(productos.getTipCuenta().equals("Ahorro") && (productos.getSaldo()-movimientos.getMonto())>=0){
				productos.setSaldo(productos.getSaldo()-movimientos.getMonto());
				productosService.actualizarSaldo(productos);
				movimientosService.guardar(movimientos);
				return movimientos;
			}
			
			//Para retirar de la cuenta corriente
			else if(productos.getTipCuenta().equals("Corriente") && (productos.getSaldo()-movimientos.getMonto())>=-2000000) {
				productos.setSaldo(productos.getSaldo()-movimientos.getMonto());
				productosService.actualizarSaldo(productos);
				movimientosService.guardar(movimientos);
				return movimientos;
			}
			
			//Si no hay saldo suficiente en la cuenta
			else {return null;} //Saldo insuficiente verifique el monto que puede retirar
		}
		
		//Para hacer transferencia entre cuentas.
		else if(productos.getEstado().equals("Activo") && movimientos.getTpMovimiento().equals("Transferencia")) {
			
			//Obtener datos de cuenta que recibe la transferencia
			Productos productosrecibir = productosService.listnumcuenta(movimientos.getPronumCuenta2());
			System.out.println(productosrecibir);
			//Para hacer el movimiento en la cuenta que recibe se instancia
			Movimientos movimientos1 = new Movimientos();
			
			//confirmar que la cuenta que envia es de ahooro si tiene saldo para enviar y si la cuenta que recibe esta activa
			if(productos.getTipCuenta().equals("Ahorro") && (productos.getSaldo()-movimientos.getMonto())>=0 && productosrecibir.getEstado().equals("Activo")) {
				//Registra movimiento y actualioza saldo quien transfiere
				productos.setSaldo(productos.getSaldo()-movimientos.getMonto());
				productosService.actualizarSaldo(productos);
				movimientosService.guardar(movimientos);
				//Para registrar movimiento quien recibe la transferencia 
				movimientos1.setDescripcion("Recibio");
				movimientos1.setFechaMovimiento(movimientos.getFechaMovimiento());
				movimientos1.setMonto(movimientos.getMonto());
				movimientos1.setPronumCuenta(movimientos.getPronumCuenta2());
				movimientos1.setPronumCuenta2(pronumCuenta);
				movimientos1.setTpMovimiento(movimientos.getTpMovimiento());
				movimientosService.guardar(movimientos1);
				//Para actualizar saldo quien recibe la transferencia
				productosrecibir.setSaldo(productosrecibir.getSaldo()+movimientos.getMonto());
				productosService.actualizarSaldo(productosrecibir);
				return movimientos;	
			}
			//Confirmar que la cuenta que envia es corriente si tiene saldo para enviar y si la cuenta que recibe esta activa
			else if(productos.getTipCuenta().equals("Corriente") && (productos.getSaldo()-movimientos.getMonto())>=-2000000 && productosrecibir.getEstado().equals("Activo")) {
				//Registra movimiento y actualioza saldo quien transfiere
				productos.setSaldo(productos.getSaldo()-movimientos.getMonto());
				productosService.actualizarSaldo(productos);
				movimientosService.guardar(movimientos);
				//Para registrar movimiento quien recibe la transferencia
				movimientos1.setDescripcion("Recibio");
				movimientos1.setFechaMovimiento(movimientos.getFechaMovimiento());
				movimientos1.setMonto(movimientos.getMonto());
				movimientos1.setPronumCuenta(movimientos.getPronumCuenta2());
				movimientos1.setPronumCuenta2(pronumCuenta);
				movimientos1.setTpMovimiento(movimientos.getTpMovimiento());
				movimientosService.guardar(movimientos1);
				//Para actualizar saldo quien recibe la transferencia
				productosrecibir.setSaldo(productosrecibir.getSaldo()+movimientos.getMonto());
				productosService.actualizarSaldo(productosrecibir);
				return movimientos;	
			}
			//Si no hay saldo suficiente en la cuenta para transferir
			else {return null;}// No posee saldo suficiente para realizar el movimiento
		}
			
		return movimientos;
	}
}
