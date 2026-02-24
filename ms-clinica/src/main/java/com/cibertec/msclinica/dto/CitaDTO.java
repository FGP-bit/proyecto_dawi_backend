package com.cibertec.msclinica.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO {

	private Long id;
    private String estado;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private BigDecimal tarifaAplicada;
    private Long pacienteId;
    private Long medicoId; 
    
    private String pacienteNombre;
    private String medicoNombre;
    
    public CitaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public BigDecimal getTarifaAplicada() {
		return tarifaAplicada;
	}

	public void setTarifaAplicada(BigDecimal tarifaAplicada) {
		this.tarifaAplicada = tarifaAplicada;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Long medicoId) {
		this.medicoId = medicoId;
	}

	public String getPacienteNombre() {
		return pacienteNombre;
	}

	public void setPacienteNombre(String pacienteNombre) {
		this.pacienteNombre = pacienteNombre;
	}

	public String getMedicoNombre() {
		return medicoNombre;
	}

	public void setMedicoNombre(String medicoNombre) {
		this.medicoNombre = medicoNombre;
	}
    
	
    
	
}
