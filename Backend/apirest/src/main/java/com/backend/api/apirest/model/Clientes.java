package com.backend.api.apirest.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Clientes {
	@Id
	@Column
	//@GeneratedValue
	private Integer numCuenta;
	@Column
	private String tipCuenta;
	@Column
	private String nombres;
	@Column
	private String apellidos;
	@Column
	private String tpIdentificacion;
	@Column
	private Integer numIdentificacion;
	@Column
	private String email;
	@Column
	private String fechaNacimiento;
	@Column
	private String fechaIngreso;
	@Column
	private String estado;
	@Column
	private Integer saldo;
	
	public Clientes(){}

	public Integer getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(Integer numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getTipCuenta() {
		return tipCuenta;
	}

	public void setTipCuenta(String tipCuenta) {
		this.tipCuenta = tipCuenta;
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

	public Integer getNumIdentificacion() {
		return numIdentificacion;
	}

	public void setNumIdentificacion(Integer numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
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

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Clientes [numCuenta=" + numCuenta + ", tipCuenta=" + tipCuenta + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", tpIdentificacion=" + tpIdentificacion + ", numIdentificacion=" + numIdentificacion
				+ ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + ", fechaIngreso=" + fechaIngreso
				+ ", estado=" + estado + ", saldo=" + saldo + "]";
	}
	
	
	
}