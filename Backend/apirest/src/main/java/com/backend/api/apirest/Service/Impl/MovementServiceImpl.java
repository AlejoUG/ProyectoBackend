package com.backend.api.apirest.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.api.apirest.Entity.CustomerEntity;
import com.backend.api.apirest.Entity.MovementEntity;
import com.backend.api.apirest.Repository.MovementRepository;
import com.backend.api.apirest.Service.CustomerService;
import com.backend.api.apirest.Service.MovementService;

@Service
public class MovementServiceImpl implements MovementService{
	
	@Autowired
	private MovementRepository movementRepository;
	
	@Transactional //Hacer movimientos clientes
	public MovementEntity guardarMovimiento(MovementEntity movimientos) throws Exception{
		return movementRepository.save(movimientos);
	}
	
	@Transactional //Obtener movimientos cliente forma descendente
	public List<MovementEntity> buscarMovimientosClientes(Long pronumCuenta) throws Exception{
		return movementRepository.buscarMovimientosClientes(pronumCuenta);
	}

	
}
