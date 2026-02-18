package com.cibertec.msclinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.gestionmedica.entity.EntradaHistorial;

public interface HistorialRepository extends JpaRepository<EntradaHistorial, Long> {
	
	List<EntradaHistorial> findByExpedienteId(Long expedienteId);

}
