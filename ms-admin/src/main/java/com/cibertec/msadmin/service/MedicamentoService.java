package com.cibertec.msadmin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.gestionmedica.entity.Medicamento;
import com.cibertec.msadmin.dto.MedicamentoDTO;
import com.cibertec.msadmin.mapper.MedicamentoMapper;
import com.cibertec.msadmin.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository repository;
	
	@Autowired
	private MedicamentoMapper mapper;
	
	public List<MedicamentoDTO> listaTodo(){
		return repository.findAll().stream()
				.map(mapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public Optional<MedicamentoDTO> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }
	
	public MedicamentoDTO guardar(MedicamentoDTO dto) {
		Medicamento entidad = mapper.toEntity(dto);
		return mapper.toDTO(repository.save(entidad));
	}
	
	public MedicamentoDTO actualizar(Long id, MedicamentoDTO dto) {
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
