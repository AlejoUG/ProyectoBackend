package com.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.Model.Clientes;


@Service
public class ClientesService {

	@Autowired
	private ClientesRepository clientesRepository;
	
	public void guardar(Clientes clientes){
		clientesRepository.save(clientes);
	}
	
}
