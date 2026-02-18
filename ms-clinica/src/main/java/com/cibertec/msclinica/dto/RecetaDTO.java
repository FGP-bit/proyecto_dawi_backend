package com.cibertec.msclinica.dto;

import java.time.LocalDate;
import java.util.List;

public class RecetaDTO {

	private Long id;
    private LocalDate fechaEmision;
    private LocalDate fechaCaducidad;
    private Long pacienteId;
    private Long medicoId;
    private List<RecetaItemDTO> items;
    
    public RecetaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
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

	public List<RecetaItemDTO> getItems() {
		return items;
	}

	public void setItems(List<RecetaItemDTO> items) {
		this.items = items;
	}
    
}
