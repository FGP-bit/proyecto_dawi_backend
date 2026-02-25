package com.cibertec.mshistorial.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.gestionmedica.entity.Enfermedad;
import com.cibertec.mshistorial.dto.EnfermedadDTO;
import com.cibertec.mshistorial.mapper.EnfermedadMapper;
import com.cibertec.mshistorial.repository.EnfermedadRepository;

@Service
public class EnfermedadService {

    @Autowired
    private EnfermedadRepository repository;

    @Autowired
    private EnfermedadMapper mapper;

    public List<EnfermedadDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    
    public EnfermedadDTO obtenerPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    public EnfermedadDTO guardar(EnfermedadDTO dto) {
        Enfermedad entidad = mapper.toEntity(dto);
        Enfermedad guardada = repository.save(entidad);
        return mapper.toDto(guardada);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}