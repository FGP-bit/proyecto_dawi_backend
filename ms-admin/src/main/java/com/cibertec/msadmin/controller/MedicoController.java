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

import com.cibertec.msadmin.dto.MedicoDTO;
import com.cibertec.msadmin.service.MedicoService;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicoController {

	@Autowired
	private MedicoService service;
	
	@GetMapping
	public List<MedicoDTO> listar(){
		return service.listarTodos();
	}
	
	@PostMapping
	public MedicoDTO crear(@RequestBody MedicoDTO dto) {
		return service.guardar(dto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoDTO> obtener(@PathVariable Long id){
		return service.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<MedicoDTO> actualizar(@PathVariable Long id, @RequestBody MedicoDTO dto){
		MedicoDTO actualizado = service.actualizar(id, dto);
		if(actualizado == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id){
		service.eliminar(id);
		return ResponseEntity.ok().build();
	}
	
}
