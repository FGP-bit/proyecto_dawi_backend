package com.cibertec.msclinica.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.cibertec.msclinica.dto.MedicamentoDTO;
import com.cibertec.msclinica.dto.MedicoDTO;
import com.cibertec.msclinica.dto.PacienteDTO;

@HttpExchange
public interface AdminClient {

	@GetExchange("/api/pacientes/{id}")
	PacienteDTO obtenerPaciente(@PathVariable("id") Long id);
	
	@GetExchange("/api/medicos/{id}")
	MedicoDTO obtenerMedico(@PathVariable("id") Long id);
	
	@GetExchange("/api/medicamento/{id}")
	MedicamentoDTO obtenerMedicamento(@PathVariable("id") Long id);
	
}