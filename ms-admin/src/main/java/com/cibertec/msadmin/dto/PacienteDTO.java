package com.cibertec.msadmin.dto;

import java.time.LocalDate;

public class PacienteDTO {

	private Long id;
    private String nombre;
    private String numeroIdentificacion;
    private LocalDate fechaNacimiento;

    
    public PacienteDTO() {
		// TODO Auto-generated constructor stub
	}
    
    public PacienteDTO(Long id, String nombre, String numeroIdentificacion, LocalDate fechaNacimiento) {
    	this.id = id;
    	this.nombre = nombre;
    	this.numeroIdentificacion = numeroIdentificacion;
    	this.fechaNacimiento = fechaNacimiento;

    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


    
    
	
}
