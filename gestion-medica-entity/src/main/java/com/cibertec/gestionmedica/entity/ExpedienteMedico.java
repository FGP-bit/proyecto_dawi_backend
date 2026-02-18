package com.cibertec.gestionmedica.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "expediente_medico")
public class ExpedienteMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "paciente_id", unique = true, nullable = false)
    private Paciente paciente;

    public ExpedienteMedico() {
    	super();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

    
}