package com.backend.api.apirest.model;

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
public class Productos {
	@Id
	@Column
	private Long numCuenta;
	@Column
	private String tipCuenta;
	@Column
	@JoinColumn(name = "numIdentificacion")
    private Long cl_numIdentificacion ;
	@Column
	private String estado;
	@Column
	private Double saldo;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fechaApertura;
	
	public Productos() {
			/*Long numCuenta, String tipCuenta, Long cl_numIdentificacion, String estado,
			Long saldo, java.util.Date fechaApertura){
		this.numCuenta = numCuenta;
        this.tipCuenta = tipCuenta;
        this.cl_numIdentificacion = cl_numIdentificacion;
        this.estado = estado;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;*/
	}

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

	public Long getCl_numIdentificacion() {
		return cl_numIdentificacion;
	}

	public void setCl_numIdentificacion(Long cl_numIdentificacion) {
		this.cl_numIdentificacion = cl_numIdentificacion;
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

	public java.util.Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(java.util.Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	@Override
	public String toString() {
		return "Productos [numCuenta=" + numCuenta + ", tipCuenta=" + tipCuenta + ", cl_numIdentificacion="
				+ cl_numIdentificacion + ", estado=" + estado + ", saldo=" + saldo + ", fechaApertura=" + fechaApertura
				+ "]";
	}
	
}
