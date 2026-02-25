package com.cibertec.msclinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.msclinica.dto.EntradaHistorialDTO;
import com.cibertec.msclinica.service.HistorialService;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    @Autowired
    private HistorialService service;

    @PostMapping
    public ResponseEntity<EntradaHistorialDTO> registrar(@RequestBody EntradaHistorialDTO dto) {
        try {
            return ResponseEntity.ok(service.registrarEntrada(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); 
        }
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<EntradaHistorialDTO>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(service.listarPorPaciente(pacienteId));
    }
}