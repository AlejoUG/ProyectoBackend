package com.backend.api.apirest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "movimientos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Movimientos {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String numCuenta;
	@Column
	private String tpMovimiento;
	@Column
    private Integer valorMonto;
	@Column
    private String fechaMovimiento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	public String getTpMovimiento() {
		return tpMovimiento;
	}
	public void setTpMovimiento(String tpMovimiento) {
		this.tpMovimiento = tpMovimiento;
	}
	public Integer getValorMonto() {
		return valorMonto;
	}
	public void setValorMonto(Integer valorMonto) {
		this.valorMonto = valorMonto;
	}
	public String getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(String fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	
	@Override
	public String toString() {
		return "Movimientos [id=" + id + ", numCuenta=" + numCuenta + ", tpMovimiento=" + tpMovimiento + ", valorMonto="
				+ valorMonto + ", fechaMovimiento=" + fechaMovimiento + "]";
	}
	
}
