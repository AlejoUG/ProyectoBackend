package com.backend.api.apirest.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.api.apirest.Entity.CustomerEntity;
import com.backend.api.apirest.Entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	/*@Query(value="select count(estado) from productos where (clidcliente=:clidcliente and exists(select estado from productos where clidcliente=clidcliente and estado=('Activo' or 'Inactivo')))", nativeQuery=true)
	int verificarEstado(@Param("clidcliente") Long clidcliente);*/
	List<ProductEntity> findByClidcliente(Long clidcliente);
	
	//public List<Productos> findAll();
	/*@Query(value="select * from productos where num_Cuenta=:numCuenta",nativeQuery=true)
	Optional<Productos> findByNumCuenta(Long numCuenta);
	//Optional<Productos> estados(@Param("numCuenta") Long numCuenta);
	
	//public Productos cambiarEstadoCancelado(Productos productos);*/
	
	/*@Modifying
	@Query(value = "UPDATE Productos SET estado='Inactivo' WHERE num_Cuenta=:numCuenta and estado='Activo'", nativeQuery=true)
	void pasarActivoInactivo(@Param("numCuenta") Long numCuenta);
	
	@Modifying
	@Query(value = "UPDATE Productos SET estado='Activo' WHERE num_Cuenta=:numCuenta and estado='Inactivo'", nativeQuery=true)
	void pasarInactivoActivo(@Param("numCuenta") Long numCuenta);
	
	@Modifying
	@Query(value = "UPDATE Productos SET estado='Cancelado' WHERE num_Cuenta=:numCuenta and saldo=0", nativeQuery=true)
	void cancelarCuenta(@Param("numCuenta") Long numCuenta);*/
	
}
