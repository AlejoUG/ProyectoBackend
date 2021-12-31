package com.backend.Model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Columns;

@Entity
@Table(name = "clientes")
public class Clientes {
	@Id
	private Integer numCuenta;
	private String tipCuenta;
	private String Nombres;
	private String Apellidos;
	private String tpIdentificacion;
	private Integer numIdentificacion;
	private String email;
	private String fechaNacimiento;
	private String fechaIngreso;
	private String estado;
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
		return Nombres;
	}

	public void setNombres(String nombres) {
		Nombres = nombres;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
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
		return "Clientes [numCuenta=" + numCuenta + ", tipCuenta=" + tipCuenta + ", Nombres=" + Nombres + ", Apellidos="
				+ Apellidos + ", tpIdentificacion=" + tpIdentificacion + ", numIdentificacion=" + numIdentificacion
				+ ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + ", fechaIngreso=" + fechaIngreso
				+ ", estado=" + estado + ", saldo=" + saldo + "]";
	}
	
}	
	 /*public clientes(int cl_numcuenta, String cl_tipcuenta, String cl_name, String cl_lastnames, String cl_tpid,
			 int cl_numid, String cl_email, String cl_fNacto, Calendar cl_fingreso, String cl_status, int cl_saldo) {
		 this.cl_numcuenta = cl_numcuenta;
		 this.cl_tipcuenta = cl_tipcuenta;
		 this.cl_name = cl_name;
		 this.cl_lastnames = cl_lastnames;
		 this.cl_tpid = cl_tpid;
		 this.cl_numid = cl_numid;
		 this.cl_email = cl_email;
		 this.cl_fNacto = cl_fNacto;
		 this.cl_fingreso = cl_fingreso;
		 this.cl_status = cl_status;
		 this.cl_saldo = cl_saldo;
	 }*/


