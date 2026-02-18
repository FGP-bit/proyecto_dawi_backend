package com.cibertec.msclinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.msclinica.dto.CitaDTO;
import com.cibertec.msclinica.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

	@Autowired
	private CitaService service;
	
	@PostMapping
	public ResponseEntity<CitaDTO> agendar(@RequestBody CitaDTO dto){
		try {
			return ResponseEntity.ok(service.registrarCita(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
