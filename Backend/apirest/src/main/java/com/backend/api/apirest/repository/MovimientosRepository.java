package com.backend.api.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.backend.api.apirest.model.Clientes;
import com.backend.api.apirest.model.Movimientos;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
	
	
	@Query("select m from Movimientos m where m.pronumCuenta=:pronumCuenta")
	List<Movimientos> buscarMovimientosClientes(@Param("pronumCuenta") Long pronumCuenta);
	public Movimientos save(Movimientos movimientos);
	/*public Optional<Movimientos> findByPronumCuenta(Long pronumCuenta);
	public Optional<Clientes> findByNumIdentificacion(Long numIdentificacion);*/
}
