package com.restaurante.restaurante.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToMany
    private List<Prato> pratos = new ArrayList<>();
    private Double totalPrecoPedido;
    private LocalDateTime data;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<Prato> getPratos() {
        return pratos;
    }
    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
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
