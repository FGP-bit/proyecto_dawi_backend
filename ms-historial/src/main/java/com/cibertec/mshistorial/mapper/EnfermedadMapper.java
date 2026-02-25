package com.cibertec.mshistorial.mapper;

import org.springframework.stereotype.Component;

import com.cibertec.gestionmedica.entity.Enfermedad;
import com.cibertec.gestionmedica.entity.Paciente;
import com.cibertec.mshistorial.dto.EnfermedadDTO;

@Component
public class EnfermedadMapper {

    public EnfermedadDTO toDto(Enfermedad entidad) {
        if (entidad == null) return null;
        
        EnfermedadDTO dto = new EnfermedadDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        
        if (entidad.getPaciente() != null) {
            dto.setPacienteId(entidad.getPaciente().getId());
            dto.setNombrePaciente(entidad.getPaciente().getNombres() + " " + 
                                  entidad.getPaciente().getApellidoPaterno() + " " + 
                                  entidad.getPaciente().getApellidoMaterno());
        }
        return dto;
    }

    public Enfermedad toEntity(EnfermedadDTO dto) {
        if (dto == null) return null;
        
        Enfermedad entidad = new Enfermedad();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        
        if (dto.getPacienteId() != null) {
            Paciente paciente = new Paciente();
            paciente.setId(dto.getPacienteId());
            entidad.setPaciente(paciente);
        }
        
        return entidad;
    }
}