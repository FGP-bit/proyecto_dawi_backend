package com.cibertec.msclinica.mapper;

import org.springframework.stereotype.Component;

import com.cibertec.gestionmedica.entity.EntradaHistorial;
import com.cibertec.msclinica.dto.EntradaHistorialDTO;

@Component
public class HistorialMapper {

	public EntradaHistorialDTO toDTO(EntradaHistorial entity) {
        if (entity == null) return null;
        EntradaHistorialDTO dto = new EntradaHistorialDTO();
        dto.setId(entity.getId());
        dto.setFechaHora(entity.getFechaHora());
        dto.setDiagnostico(entity.getDiagnostico());
        dto.setTratamiento(entity.getTratamiento());
        dto.setMedicoId(entity.getMedico().getId());

        dto.setPacienteId(entity.getExpediente().getPaciente().getId());
        return dto;
    }
}
