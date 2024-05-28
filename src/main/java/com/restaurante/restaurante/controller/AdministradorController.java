package com.restaurante.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.restaurante.restaurante.model.Prato;
import com.restaurante.restaurante.repository.PedidoRepository;
import com.restaurante.restaurante.repository.PratoRepository;

@Controller
public class AdministradorController {
    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/administrador")
    public String showAdminPanel(Model model) {
        model.addAttribute("pratos", pratoRepository.findAll());
        model.addAttribute("novoPrato", new Prato());
        return "adminPanel";
    }
    @PostMapping("/administrador")
    public String createPrato(Prato prato) {
        pratoRepository.save(prato);
        return "redirect:/administrador";
    }

    @GetMapping("/administrador/editar/{id}")
    public String showEditPratoForm(@PathVariable Long id, Model model) {
        model.addAttribute("prato", pratoRepository.findById(id).orElse(new Prato()));
        return "editPrato";
    }
    @PostMapping("/administrador/editar/{id}")
    public String editPrato(@PathVariable Long id, Prato prato) {
        prato.setId(id);
        pratoRepository.save(prato);
        return "redirect:/administrador";
    }

    @GetMapping("/administrador/excluir/{id}")
    public String deletePrato(@PathVariable Long id) {
        pratoRepository.deleteById(id);
        return "redirect:/administrador";
    }
    @GetMapping("/administrador/historico-pedidos")
    public String showHistoricoPedidos(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "historicoPedidos";
    }
}
