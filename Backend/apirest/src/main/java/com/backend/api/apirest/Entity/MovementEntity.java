package com.backend.api.apirest.Entity;

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
public class MovementEntity {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idmovimiento;
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
    private Double gmf;
	@Column
	//@Temporal(TemporalType.TIMESTAMP)
    private String fechaMovimiento;
	@Column
	private String descripcion;
	
	public MovementEntity(){}
	
	public Double getGmf() {
		return gmf;
	}

	public void setGmf(Double gmf) {
		this.gmf = gmf;
	}

	public Long getIdmovimiento() {
		return idmovimiento;
	}


	public void setIdmovimiento(Long idmovimiento) {
		this.idmovimiento = idmovimiento;
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
	
}
