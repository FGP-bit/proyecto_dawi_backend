package com.cibertec.msclinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.gestionmedica.entity.RecetaItem;

public interface RecetaItemRepository extends JpaRepository<RecetaItem, Long> {

}
