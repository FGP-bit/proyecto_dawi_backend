package com.cibertec.msclinica.mapper;

import org.springframework.stereotype.Component;

import com.cibertec.gestionmedica.entity.Cita;
import com.cibertec.gestionmedica.entity.Medico;
import com.cibertec.gestionmedica.entity.Paciente;
import com.cibertec.msclinica.dto.CitaDTO;

@Component
public class CitaMapper {

	public CitaDTO toDTO(Cita entity) {
        if (entity == null) return null;
        CitaDTO dto = new CitaDTO();
        dto.setId(entity.getId());
        dto.setEstado(entity.getEstado());
        dto.setFecha(entity.getFecha());
        dto.setHora(entity.getHora());
        dto.setMotivo(entity.getMotivo());
        dto.setTarifaAplicada(entity.getTarifaAplicada());
        dto.setPacienteId(entity.getPaciente().getId());
        dto.setMedicoId(entity.getMedico().getId());
        if(entity.getPaciente() != null) {
        	dto.setPacienteNombre(entity.getPaciente().getNombres() + " " + entity.getPaciente().getApellidoPaterno());
        }
        if(entity.getMedico() != null) {
            dto.setMedicoNombre("Dr. " + entity.getMedico().getNombre());
        }
        return dto;
    }
	
	public Cita toEntity(CitaDTO dto) {
			
		if(dto == null) return null;
		Cita entity = new Cita();
		entity.setId(dto.getId());
		entity.setEstado(dto.getEstado());
		entity.setFecha(dto.getFecha());
		entity.setHora(dto.getHora());
		entity.setMotivo(dto.getMotivo());
		entity.setTarifaAplicada(dto.getTarifaAplicada());
		
		
		Paciente p = new Paciente(); p.setId(dto.getPacienteId());
		entity.setPaciente(p);

		Medico m = new Medico(); m.setId(dto.getMedicoId());
		entity.setMedico(m);
		
		return entity;
		
		}
	}
