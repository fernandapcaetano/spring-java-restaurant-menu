package com.restaurante.restaurante.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.restaurante.restaurante.model.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long>{

    
}
