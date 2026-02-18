package com.cibertec.msadmin.mapper;

import org.springframework.stereotype.Component;

import com.cibertec.gestionmedica.entity.Paciente;
import com.cibertec.msadmin.dto.PacienteDTO;

@Component
public class PacienteMapper {

	//de entidad a dto ( mostrar ) 
	public PacienteDTO toDTO(Paciente entity) {
        if (entity == null) return null;
        return new PacienteDTO(
            entity.getId(),
            entity.getNombre(),
            entity.getNumeroIdentificacion(),
            entity.getFechaNacimiento()
        );
    }
	//de dto a entidad para guardar datos
	
	public Paciente toEntity(PacienteDTO dto) {
		if(dto == null) return null;
		Paciente entity = new Paciente();
		entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        return entity;
	}
	
}
