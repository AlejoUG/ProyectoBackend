package com.backend.api.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.api.apirest.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
	
	@Modifying
	@Query(value = "delete from Clientes where num_Identificacion=:num_Identificacion and exists (select estado from Productos p where p.cl_num_Identificacion=:num_Identificacion and p.estado='Cancelado')", nativeQuery=true)
	void eliminarClientesCancelados(@Param("num_Identificacion") Long num_Identificacion);
	public Clientes save(Clientes clientes);
	public Optional<Clientes> findByNumIdentificacion(Long numIdentificacion);
	public List<Clientes> findAll();
	//@Query("select c from Clientes c where c.numCuenta=:numCuenta and c.estado=:estado")
	//List<Clientes> buscarClientesActivos(@Param("numCuenta") Integer numCuenta, @Param("estado") String estado); //
		
}
