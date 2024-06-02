package com.restaurante.restaurante.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.restaurante.model.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
