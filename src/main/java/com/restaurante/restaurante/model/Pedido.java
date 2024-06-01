package com.restaurante.restaurante.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int mesa;
    @ManyToMany
    private List<Prato> pratos;
    private Integer quantidadePrato;
    private Double totalPrecoPedido;
    private LocalDateTime data;

    //getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getMesa() {
        return mesa;
    }
    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
    public List<Prato> getPratos() {
        return pratos;
    }
    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }
    public int getQuantidadePrato() {
        return quantidadePrato;
    }
    public void setQuantidadePrato(int quantidadePrato) {
        this.quantidadePrato = quantidadePrato;
    }
    public Double getTotalPrecoPedido() {
        return totalPrecoPedido;
    }
    public void setTotalPrecoPedido(Double totalPrecoPedido) {
        this.totalPrecoPedido = totalPrecoPedido;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }


    
}
