package com.backend.api.apirest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "movimientos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movimientos {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@JoinColumn(name = "numCuenta")
	private Long pronumCuenta;
	@Column
	private String tpMovimiento;
	@Column
    private Double monto;
	@Column
	//@Temporal(TemporalType.TIMESTAMP)
    private String fechaMovimiento;
	
	public Movimientos(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPro_numCuenta() {
		return pronumCuenta;
	}

	public void setPro_numCuenta(Long pronumCuenta) {
		this.pronumCuenta = pronumCuenta;
	}

	public String getTpMovimiento() {
		return tpMovimiento;
	}

	public void setTpMovimiento(String tpMovimiento) {
		this.tpMovimiento = tpMovimiento;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(String fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	@Override
	public String toString() {
		return "Movimientos [id=" + id + ", pronumCuenta=" + pronumCuenta + ", tpMovimiento=" + tpMovimiento
				+ ", monto=" + monto + ", fechaMovimiento=" + fechaMovimiento + "]";
	}
	
}
