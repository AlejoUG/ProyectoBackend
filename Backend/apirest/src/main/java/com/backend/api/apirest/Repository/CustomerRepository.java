package com.backend.api.apirest.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.api.apirest.Entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	@Modifying
	@Query(value = "delete from Clientes where idcliente=:idcliente and !exists (select estado from Productos p where p.clidcliente=:idcliente and p.estado=('Activo' or 'Inactivo')));", nativeQuery=true)
	void eliminarClientesCancelados(@Param("idcliente") Long idcliente);
	//@Query("select c from Clientes c where c.numCuenta=:numCuenta and c.estado=:estado")
	//List<Clientes> buscarClientesActivos(@Param("numCuenta") Integer numCuenta, @Param("estado") String estado); //		
}
