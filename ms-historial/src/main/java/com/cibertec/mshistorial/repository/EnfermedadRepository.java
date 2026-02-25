package com.cibertec.mshistorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.gestionmedica.entity.Enfermedad;

public interface EnfermedadRepository extends JpaRepository<Enfermedad, Long> {

}
