package com.cibertec.msadmin.mapper;

import org.springframework.stereotype.Component;

import com.cibertec.gestionmedica.entity.Medico;
import com.cibertec.msadmin.dto.MedicoDTO;

@Component
public class MedicoMapper {

    public MedicoDTO toDTO(Medico entity) {
        if(entity == null) return null;
      
        return new MedicoDTO(entity.getId(), entity.getNombre(), entity.getApellido(), entity.getEspecialidad(), entity.getTelefono());
    }
    
    public Medico toEntity(MedicoDTO dto) {
        if(dto == null) return null;
        Medico entity = new Medico();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido()); 
        entity.setEspecialidad(dto.getEspecialidad());
        entity.setTelefono(dto.getTelefono()); 
        return entity;
    }
}
