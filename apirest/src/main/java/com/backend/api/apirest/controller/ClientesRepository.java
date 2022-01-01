package com.backend.api.apirest.controller;

import java.util.List;

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
	
	@Modifying
	@Query("update Clientes c set c.nombres=:nombres, c.apellidos=:apellidos, c.tpIdentificacion=:tpIdentificacion, c.numIdentificacion=:numIdentificacion, c.email=:email, c.fechaNacimiento=:fechaNacimiento WHERE c.numCuenta=:numCuenta")
	void setClientesInfoById(@Param("nombres") String nombres,@Param("apellidos") String apellidos, @Param("tpIdentificacion") String tpIdentificacion, 
			@Param("numIdentificacion") Integer numIdentificacion, @Param("email") String email,@Param("fechaNacimiento") String fechaNacimiento, @Param("numCuenta") Integer numCuenta);
}
