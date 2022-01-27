package com.backend.api.apirest.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "productos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductEntity {
	@Id
	@Column
	private Long numCuenta;
	@Column
	private String tipCuenta;
	@Column
	@JoinColumn(name = "idcliente")
    private Long clidcliente;
	@Column
	private String estado;
	@Column
	private Double saldo;
	@Column
	//@Temporal(TemporalType.TIMESTAMP)
	private String fechaApertura;
	
	public ProductEntity() {}
	
	public ProductEntity(Long numCuenta, String tipCuenta, Long clidcliente, String estado,
	Double saldo, String fechaApertura){
		this.numCuenta = numCuenta;
		this.tipCuenta = tipCuenta;
		this.clidcliente = clidcliente;
		this.estado = estado;
		this.saldo = saldo;
		this.fechaApertura = fechaApertura;}

	public Long getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getTipCuenta() {
		return tipCuenta;
	}

	public void setTipCuenta(String tipCuenta) {
		this.tipCuenta = tipCuenta;
	}

	public Long getClidcliente() {
		return clidcliente;
	}

	public void setClidcliente(Long clidcliente) {
		this.clidcliente = clidcliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	
}
