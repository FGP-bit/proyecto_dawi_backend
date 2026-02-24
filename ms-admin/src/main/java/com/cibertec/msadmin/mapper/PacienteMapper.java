package com.cibertec.msadmin.mapper;

import org.springframework.stereotype.Component;

import com.cibertec.gestionmedica.entity.Paciente;
import com.cibertec.msadmin.dto.PacienteDTO;

@Component
public class PacienteMapper {

    public PacienteDTO toDTO(Paciente entity) {
        if (entity == null) return null;
        return new PacienteDTO(
            entity.getId(),
            entity.getNombres(),
            entity.getApellidoPaterno(),
            entity.getApellidoMaterno(),
            entity.getNumeroIdentificacion(),
            entity.getFechaNacimiento(),
            entity.getTelefono(),
            entity.getDireccion()
        );
    }
    
    public Paciente toEntity(PacienteDTO dto) {
        if(dto == null) return null;
        Paciente entity = new Paciente();
        entity.setId(dto.getId());
        entity.setNombres(dto.getNombres());
        entity.setApellidoPaterno(dto.getApellidoPaterno());
        entity.setApellidoMaterno(dto.getApellidoMaterno());
        entity.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setTelefono(dto.getTelefono());
        entity.setDireccion(dto.getDireccion());
        return entity;
    }
}