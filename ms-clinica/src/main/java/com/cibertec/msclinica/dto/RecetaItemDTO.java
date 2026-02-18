package com.cibertec.msclinica.dto;

public class RecetaItemDTO {
	private Long medicamentoId;
    private String dosis;
    private String frecuencia;
    
    public RecetaItemDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getMedicamentoId() {
		return medicamentoId;
	}

	public void setMedicamentoId(Long medicamentoId) {
		this.medicamentoId = medicamentoId;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
    
    
}
