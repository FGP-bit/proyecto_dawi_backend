package com.cibertec.msclinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.gestionmedica.entity.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long>{

}
