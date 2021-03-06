package com.backend.api.apirest.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerEntity {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcliente;
	@Column
	private Long numIdentificacion;
	@Column
	private String nombres;
	@Column
	private String apellidos;
	@Column
	private String tpIdentificacion;
	@Column
	private String email;
	@Column
	private String fechaNacimiento;
	@Column
	private String fechaCreacion;
	
	public CustomerEntity(){}

	public CustomerEntity(Long idcliente, Long numIdentificacion, String nombres, String apellidos,
			String tpIdentificacion, String email, String fechaNacimiento, String fechaCreacion) {
		super();
		this.idcliente = idcliente;
		this.numIdentificacion = numIdentificacion;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tpIdentificacion = tpIdentificacion;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	public Long getNumIdentificacion() {
		return numIdentificacion;
	}

	public void setNumIdentificacion(Long numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTpIdentificacion() {
		return tpIdentificacion;
	}

	public void setTpIdentificacion(String tpIdentificacion) {
		this.tpIdentificacion = tpIdentificacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	@Override
	public String toString() {
		return "Clientes [numIdentificacion=" + numIdentificacion + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", tpIdentificacion=" + tpIdentificacion + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}
	
}