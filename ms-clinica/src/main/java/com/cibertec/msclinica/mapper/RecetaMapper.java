package com.cibertec.msclinica.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cibertec.gestionmedica.entity.Medicamento;
import com.cibertec.gestionmedica.entity.Medico;
import com.cibertec.gestionmedica.entity.Paciente;
import com.cibertec.gestionmedica.entity.Receta;
import com.cibertec.gestionmedica.entity.RecetaItem;
import com.cibertec.msclinica.dto.RecetaDTO;
import com.cibertec.msclinica.dto.RecetaItemDTO;

@Component
public class RecetaMapper {

	public RecetaDTO toDTO(Receta entity) {
		if(entity == null) return null;
		RecetaDTO dto = new RecetaDTO();
		dto.setId(entity.getId());
        dto.setFechaEmision(entity.getFechaEmision());
        dto.setFechaCaducidad(entity.getFechaCaducidad());
        dto.setPacienteId(entity.getPaciente().getId());
        dto.setMedicoId(entity.getMedico().getId());
        
        //mapear los items
        if(entity.getItems()!= null) {
        	List<RecetaItemDTO> itemsDto = entity.getItems().stream().map(item ->{
        		RecetaItemDTO iDto = new RecetaItemDTO();
        		iDto.setMedicamentoId(item.getMedicamento().getId());
        		iDto.setDosis(item.getDosis());
        		iDto.setFrecuencia(item.getFrecuencia());
        		return iDto;
        	}).collect(Collectors.toList());
        	dto.setItems(itemsDto);
        }
        return dto;
	}
	
	public Receta toEntity(RecetaDTO dto) {
		if(dto == null ) return null;
		Receta entity = new Receta();
		entity.setId(dto.getId());
		entity.setFechaEmision(dto.getFechaEmision());
		entity.setFechaCaducidad(dto.getFechaCaducidad());
		
		//ref por id  de los dummy obj
		
		Paciente p =  new Paciente(); p.setId(dto.getPacienteId());
		entity.setPaciente(p);
		Medico m = new Medico(); m.setId(dto.getMedicoId()) ;
		entity.setMedico(m);
		
		//map items
		if(dto.getItems() == null) {
			List<RecetaItem> items = new ArrayList<>();
			for(RecetaItemDTO itemDto : dto.getItems()){
				RecetaItem item = new RecetaItem();
				item.setDosis(itemDto.getDosis());
				item.setFrecuencia(itemDto.getFrecuencia());
				
				Medicamento med  = new Medicamento(); med.setId(itemDto.getMedicamentoId());
				item.setMedicamento(med);
				
				item.setReceta(entity); 
				items.add(item);
			}
			entity.setItems(items);
		}
		return entity;
		
		
	}
	
	
}
