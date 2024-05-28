package com.restaurante.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.restaurante.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    boolean existsByCpf(String cpf);

    Cliente findByCpf(String cpf);
    
} 
