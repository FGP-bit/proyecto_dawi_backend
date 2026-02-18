package com.cibertec.msadmin.mapper;

import org.springframework.stereotype.Component;

import com.cibertec.gestionmedica.entity.Medicamento;
import com.cibertec.msadmin.dto.MedicamentoDTO;

@Component
public class MedicamentoMapper {

	
	public MedicamentoDTO toDTO(Medicamento entity) {
		if(entity == null) return null;
		return new MedicamentoDTO(entity.getId(), entity.getNombre(), entity.getDescripcion());
	}
	
	public Medicamento toEntity(MedicamentoDTO dto) {
		if(dto == null) return null;
		
		Medicamento entity  = new Medicamento();
		entity.setId(dto.getId());
		entity.setNombre(dto.getNombre());
		entity.setDescripcion(dto.getDescripcion());
		return entity;
	}
	
	
}
