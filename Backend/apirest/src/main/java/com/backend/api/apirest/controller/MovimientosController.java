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
import com.backend.api.apirest.service.MovimientosService;

@RestController
@RequestMapping("api/clientes/movimientos")
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
public class MovimientosController {
	
	@Autowired
	private MovimientosService movimientosService;
	
	@PostMapping("/consignacion/{numCuenta}")
	public Movimientos guardarMovimientos(@PathVariable("numCuenta") Long numCuenta,@RequestBody Movimientos movimientos) {
		System.out.println(movimientos);
		movimientosService.guardar(movimientos);
		return movimientos;
	}
	
	@GetMapping("/{pronumCuenta}") //Obtener movimientos cuenta clientes
	public List<Movimientos> buscarMovimientosClientes(@PathVariable("pronumCuenta") Long pronumCuenta) {
		return movimientosService.buscarMovimientosClientes(pronumCuenta);
	}
	
	/*/ Get all operations
		@CrossOrigin(origins = "http://localhost:8100")
		@GetMapping("/operation")
		public List<Operation> getAllOperations() {
			return operationRepository.findAll();
		}
		
		// Get operation by idaccount
		@CrossOrigin(origins = "http://localhost:8100")
		@GetMapping("/operation/{idaccount}")
		public List<Operation> getByAccountc(@PathVariable Long idaccount) {
			return operationRepository.findByAccount(idaccount);
		}
		
		// Create operation
		@CrossOrigin(origins = "http://localhost:8100")
		@PostMapping("/operation")
		public Operation createOperation(@RequestBody Operation operation) {
			return operationRepository.save(operation);
		}
		
		// Operation types
		@CrossOrigin(origins = "http://localhost:8100")
		@GetMapping("/operation/types")
		public List<Map<String, Object>> operationTypes() {
			List<Map<String, Object>> response = new ArrayList<Map<String,Object>>();
			for (Object[] data : operationRepository.getOperationTypes()) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", data[0]);
				map.put("type", data[1]);
				response.add(map);
			}
			return response;
		}*/

}
