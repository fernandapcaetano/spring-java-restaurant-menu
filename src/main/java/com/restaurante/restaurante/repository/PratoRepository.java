package com.restaurante.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.restaurante.model.Prato;

public interface PratoRepository extends JpaRepository<Prato, Long>{
    
}
