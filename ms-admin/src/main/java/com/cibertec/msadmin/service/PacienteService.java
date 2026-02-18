package com.cibertec.msadmin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.gestionmedica.entity.Paciente;
import com.cibertec.msadmin.dto.PacienteDTO;
import com.cibertec.msadmin.mapper.PacienteMapper;
import com.cibertec.msadmin.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;
	
	@Autowired
	private PacienteMapper mapper;
	
	//retorna la lista de dto
	
	public List<PacienteDTO> listarTodos(){
		return repository.findAll().stream()
				.map(mapper::toDTO) //convertir cada entidad a dto
				.collect(Collectors.toList());
	}
	
	public Optional<PacienteDTO> buscarPorId(Long id){
		return repository.findById(id).map(mapper::toDTO);
	}
	//recibir dto y guardar entidad y que retorne dto
	public PacienteDTO guardar(PacienteDTO pacienteDTO) {
		Paciente entidad = mapper.toEntity(pacienteDTO);
		Paciente guardado = repository.save(entidad);
		return mapper.toDTO(guardado);
		
	}
	
	public PacienteDTO actualizar(Long id, PacienteDTO pacienteDTO) {
		if(repository.existsById(id)) {
			pacienteDTO.setId(id); 
			Paciente entidad = mapper.toEntity(pacienteDTO);
			Paciente actualizado = repository.save(entidad);
			return mapper.toDTO(actualizado);
		}
		return null;
	}
	
	public void eliminar(Long id) {
		
		repository.deleteById(id);
	}
	
}
