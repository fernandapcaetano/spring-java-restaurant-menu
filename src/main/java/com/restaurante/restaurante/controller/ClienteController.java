package com.restaurante.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.restaurante.restaurante.model.Cliente;
import com.restaurante.restaurante.repository.ClienteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ClienteController {
    
    @Autowired ClienteRepository clienteRepository;

    @GetMapping("/")
    public String showLoginForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clienteForm";
    }

    @PostMapping("/")
    public String login(Cliente cliente, HttpSession session) {
        Cliente foundCliente = clienteRepository.findByCpf(cliente.getCpf());
        if (foundCliente != null) {
            session.setAttribute("clienteId", foundCliente.getId());
            session.setAttribute("clienteNome", foundCliente.getNome());
        } else {
            Cliente savedCliente = clienteRepository.save(cliente);
            session.setAttribute("clienteId", savedCliente.getId());
            session.setAttribute("clienteNome", savedCliente.getNome());
        }
        return "redirect:/cardapio";
    }
    
}
