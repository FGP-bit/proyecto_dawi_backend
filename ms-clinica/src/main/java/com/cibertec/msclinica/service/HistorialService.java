package com.cibertec.msclinica.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.gestionmedica.entity.EntradaHistorial;
import com.cibertec.gestionmedica.entity.ExpedienteMedico;
import com.cibertec.gestionmedica.entity.Medico;
import com.cibertec.gestionmedica.entity.Paciente;
import com.cibertec.msclinica.client.AdminClient;
import com.cibertec.msclinica.dto.EntradaHistorialDTO;
import com.cibertec.msclinica.mapper.HistorialMapper;
import com.cibertec.msclinica.repository.ExpedienteRepository;
import com.cibertec.msclinica.repository.HistorialRepository;

@Service
public class HistorialService {

	@Autowired
	private HistorialRepository historialRepo;
	
	@Autowired
	private ExpedienteRepository expedienteRepo;
	
	@Autowired
	private HistorialMapper mapper;
	
	@Autowired
	private AdminClient adminClient;
	
	@Transactional
    public EntradaHistorialDTO registrarEntrada(EntradaHistorialDTO dto) {
        
        adminClient.obtenerPaciente(dto.getPacienteId());

        
        ExpedienteMedico expediente = expedienteRepo.findByPacienteId(dto.getPacienteId())
            .orElseGet(() -> {
              
                ExpedienteMedico nuevo = new ExpedienteMedico();
                Paciente p = new Paciente(); p.setId(dto.getPacienteId());
                nuevo.setPaciente(p);
                return expedienteRepo.save(nuevo);
            });

     
        EntradaHistorial entrada = new EntradaHistorial();
        entrada.setFechaHora(LocalDateTime.now());
        entrada.setDiagnostico(dto.getDiagnostico());
        entrada.setTratamiento(dto.getTratamiento());
        entrada.setExpediente(expediente);
        
        Medico m = new Medico(); m.setId(dto.getMedicoId());
        entrada.setMedico(m);

        EntradaHistorial guardado = historialRepo.save(entrada);
        return mapper.toDTO(guardado);
    }
	
	public List<EntradaHistorialDTO> listarPorPaciente(Long pacienteId) {
        ExpedienteMedico expediente = expedienteRepo.findByPacienteId(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente sin expediente"));
        
        return historialRepo.findByExpedienteId(expediente.getId()).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
	
	
	
}
