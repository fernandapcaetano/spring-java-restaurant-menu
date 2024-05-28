package com.restaurante.restaurante.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurante.restaurante.model.Cliente;
import com.restaurante.restaurante.model.Pedido;
import com.restaurante.restaurante.model.Prato;
import com.restaurante.restaurante.repository.ClienteRepository;
import com.restaurante.restaurante.repository.PedidoRepository;
import com.restaurante.restaurante.repository.PratoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CardapioController {
    
    @Autowired PratoRepository pratoRepository;
    @Autowired PedidoRepository pedidoRepository;
    @Autowired ClienteRepository clienteRepository;

    @GetMapping("/cardapio")
    public String showCardapio(Model model, HttpSession session) {
        Long clienteId = (Long) session.getAttribute("clienteId");
        if (clienteId == null) {
            return "redirect:/"; // Redireciona para a página de login se o ID do cliente não estiver na sessão
        }
        model.addAttribute("pratos", pratoRepository.findAll());
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clienteId", clienteId); // Passa o clienteId para o formulário
        return "cardapio";
    }
    @PostMapping("/cardapio")
    public String confirmPedido(Pedido pedido, @RequestParam Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                                            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente); // Associando o cliente ao pedido
        pedido.setData(LocalDateTime.now());
        Double totalPrecoPedido = pedido.getPratos().stream().mapToDouble(Prato::getPreco).sum();
        pedido.setTotalPrecoPedido(totalPrecoPedido);
        pedidoRepository.save(pedido);
        return "redirect:/cardapio/success";
    }

    @GetMapping("/cardapio/total")
    @ResponseBody
    public Double calculateTotal(@RequestParam List<Long> pratoIds) {
        List<Prato> pratos = pratoRepository.findAllById(pratoIds);
        return pratos.stream().mapToDouble(Prato::getPreco).sum();
    }

    @GetMapping("/cardapio/success")
    public String showSuccess(Model model, HttpSession session) {
        String clienteNome = (String) session.getAttribute("clienteNome"); // Obtém o nome do cliente da sessão
        model.addAttribute("clienteNome", clienteNome);
        return "pedidoSuccess";
    }
}
