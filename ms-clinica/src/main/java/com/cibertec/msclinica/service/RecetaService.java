package com.cibertec.msclinica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.gestionmedica.entity.Receta;
import com.cibertec.msclinica.client.AdminClient;
import com.cibertec.msclinica.dto.RecetaDTO;
import com.cibertec.msclinica.mapper.RecetaMapper;
import com.cibertec.msclinica.repository.RecetaRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RecetaService {

	
	@Autowired
	private RecetaRepository repository;
	
	@Autowired
	private RecetaMapper mapper;
	
	@Autowired
	private AdminClient adminClient;
	
	@Transactional
	public RecetaDTO generarReceta(RecetaDTO dto) {
		adminClient.obtenerPaciente(dto.getPacienteId());
		adminClient.obtenerMedico(dto.getMedicoId());
		
		if(dto.getItems() != null) {
			dto.getItems().forEach(item -> {
				adminClient.obtenerMedicamento(item.getMedicamentoId());
			});
		}
		
		Receta receta = mapper.toEntity(dto);
		Receta guardada = repository.save(receta);
		return mapper.toDTO(guardada);
	}
	
}
