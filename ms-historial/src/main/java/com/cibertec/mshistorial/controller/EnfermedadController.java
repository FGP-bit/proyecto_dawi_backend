package com.cibertec.mshistorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.mshistorial.dto.EnfermedadDTO;
import com.cibertec.mshistorial.service.EnfermedadService;

@RestController
@RequestMapping("/api/enfermedades")
public class EnfermedadController {

    @Autowired
    private EnfermedadService service;

    @GetMapping
    public ResponseEntity<List<EnfermedadDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EnfermedadDTO> obtenerPorId(@PathVariable Long id) {
        EnfermedadDTO dto = service.obtenerPorId(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EnfermedadDTO> registrar(@RequestBody EnfermedadDTO dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EnfermedadDTO> actualizar(@PathVariable Long id, @RequestBody EnfermedadDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}