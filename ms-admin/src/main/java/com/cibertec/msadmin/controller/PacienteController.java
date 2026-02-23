package com.cibertec.msadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cibertec.msadmin.dto.PacienteDTO;
import com.cibertec.msadmin.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteController {

	@Autowired
	private PacienteService service;
	
	@GetMapping
	public List<PacienteDTO> listar(){
		return service.listarTodos();
	}
	
	@PostMapping
	public PacienteDTO crear(@RequestBody PacienteDTO dto) {
		return service.guardar(dto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PacienteDTO> obertener(@PathVariable Long id){
		return service.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<PacienteDTO> actualizar(@PathVariable Long id, @RequestBody PacienteDTO dto){
		PacienteDTO actualizado = service.actualizar(id, dto);
		if(actualizado == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id){
		service.eliminar(id);
		return ResponseEntity.ok().build();
	}
	
}
