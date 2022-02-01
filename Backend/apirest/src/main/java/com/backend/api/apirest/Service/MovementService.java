package com.backend.api.apirest.Service;

import java.util.List;

import com.backend.api.apirest.Entity.MovementEntity;

public interface MovementService {
	
	public MovementEntity guardarMovimiento(MovementEntity movimientos) throws Exception;
	public List<MovementEntity> buscarMovimientosClientes(Long pronumCuenta) throws Exception;
	
}
