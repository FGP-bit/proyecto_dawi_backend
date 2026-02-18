package com.cibertec.msclinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.msclinica.dto.RecetaDTO;
import com.cibertec.msclinica.service.RecetaService;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {
	
	@Autowired
	private RecetaService service;
	
	@PostMapping
	public ResponseEntity<RecetaDTO> crear(@RequestBody RecetaDTO dto){
		return ResponseEntity.ok(service.generarReceta(dto));
	}   

}
