package com.backend.Crud.Controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.Model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer>{

}