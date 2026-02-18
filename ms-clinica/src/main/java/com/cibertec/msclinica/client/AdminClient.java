package com.cibertec.msclinica.client;


import com.cibertec.msclinica.dto.MedicamentoDTO;
import com.cibertec.msclinica.dto.MedicoDTO;
import com.cibertec.msclinica.dto.PacienteDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-admin", url = "http://localhost:8001")
public interface AdminClient {

	@GetMapping("/api/pacientes/{id}")
	PacienteDTO obtenerPaciente(@PathVariable("id") Long id);
	
	@GetMapping("/api/medicos/{id}")
	MedicoDTO obtenerMedico(@PathVariable("id") Long id);
	
	@GetMapping("/api/medicamento/{id}")
	MedicamentoDTO obtenerMedicamento(@PathVariable("id") Long id);
	
}