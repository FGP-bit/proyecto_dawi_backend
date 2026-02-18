package com.cibertec.msadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.msadmin.dto.MedicamentoDTO;
import com.cibertec.msadmin.service.MedicamentoService;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

	@Autowired
	private MedicamentoService service;
	
	@GetMapping
	public List<MedicamentoDTO> listar(){
		return service.listaTodo();
	}
	
	@PostMapping
	public MedicamentoDTO crear(@RequestBody MedicamentoDTO dto) {
		return service.guardar(dto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicamentoDTO> obtener(@PathVariable Long id){
		return service.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<MedicamentoDTO> actualizar(@PathVariable Long id ,@RequestBody MedicamentoDTO dto){
		MedicamentoDTO actualizado = service.actualizar(id, dto);
		if(actualizado == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> eliminar(@PathVariable Long id){
		service.eliminar(id);
		return ResponseEntity.ok().build();
	}
	
}
