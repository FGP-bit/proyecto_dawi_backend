package com.cibertec.msadmin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.gestionmedica.entity.Medico;
import com.cibertec.msadmin.dto.MedicoDTO;
import com.cibertec.msadmin.mapper.MedicoMapper;
import com.cibertec.msadmin.repository.MedicoRepository;

@Service
public class MedicoService {

	
	@Autowired
	private MedicoRepository repository;
	
	@Autowired
	private MedicoMapper mapper;
	
	public List<MedicoDTO> listarTodos() {
		return repository.findAll().stream()
				.map(mapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public Optional<MedicoDTO> buscarPorId(Long id){
		return repository.findById(id).map(mapper::toDTO);
	}
	
	public MedicoDTO guardar(MedicoDTO dto) {
		Medico medico = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(medico));
	}
	
	public MedicoDTO actualizar(Long id, MedicoDTO dto) {
		if(repository.existsById(id)) {
			dto.setId(id);
			return guardar(dto);
		}
		return null;
		
	}
	
	public void eliminar(Long id) {
		repository.deleteById(id);
	}
}
