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
	@JoinColumn(name = "numCuenta")
	private Long pronumCuenta2;
	@Column
	private String tpMovimiento;
	@Column
    private Double monto;
	@Column
	//@Temporal(TemporalType.TIMESTAMP)
    private String fechaMovimiento;
	@Column
	private String descripcion;
	
	public Movimientos(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPronumCuenta() {
		return pronumCuenta;
	}

	public void setPronumCuenta(Long pronumCuenta) {
		this.pronumCuenta = pronumCuenta;
	}

	public Long getPronumCuenta2() {
		return pronumCuenta2;
	}

	public void setPronumCuenta2(Long pronumCuenta2) {
		this.pronumCuenta2 = pronumCuenta2;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		return "Movimientos [id=" + id + ", pronumCuenta=" + pronumCuenta + ", pronumCuenta2=" + pronumCuenta2
				+ ", tpMovimiento=" + tpMovimiento + ", monto=" + monto + ", fechaMovimiento=" + fechaMovimiento
				+ ", descripcion=" + descripcion + "]";
	}
	
}
