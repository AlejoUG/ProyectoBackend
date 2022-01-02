package com.backend.api.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.api.apirest.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
	
	@Query("select c from Clientes c where c.numCuenta=:numCuenta and c.estado=:estado")
	List<Clientes> buscarClientesActivos(@Param("numCuenta") Integer numCuenta, @Param("estado") String estado); //
	
	public Clientes save(Clientes clientes);
	public Optional<Clientes> findByNumCuenta(Integer numCuenta);
		
}
