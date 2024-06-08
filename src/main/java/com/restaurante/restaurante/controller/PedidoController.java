package com.restaurante.restaurante.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurante.restaurante.model.Pedido;
import com.restaurante.restaurante.model.Prato;
import com.restaurante.restaurante.repository.PedidoRepository;
import com.restaurante.restaurante.repository.PratoRepository;


@Controller
public class PedidoController {
    @Autowired PratoRepository pratoRepository;
    @Autowired PedidoRepository pedidoRepository;

    //escolhendo mesa
    @GetMapping("/")
    public String escolherMesa(Model model) {
        return "mesa";
    }

    //cardapio
    @GetMapping("/cardapio")
    public String cardapio(Model model) {
        model.addAttribute("pratos", pratoRepository.findAll());//procurando todos os pratos
        model.addAttribute("pedido", new Pedido());//fazendo um novo pedido
        return "cardapio";
    }

    @PostMapping("/cardapio")
    public String fazendoPedido(Pedido pedido, int mesa, Integer quantidadePrato, @RequestParam List<Long> pratos) {
        pedido.setData(LocalDateTime.now());
        pedido.setMesa(mesa);
        pedido.setQuantidadePrato(quantidadePrato != null ? quantidadePrato : 0);
        pedido.setPratos(pratoRepository.findAllById(pratos));
        Double totalPrecoPedido = 0.0;
        List<Prato> pratosSelecionados = pedido.getPratos();
        for (Prato prato : pratosSelecionados) {
            totalPrecoPedido += prato.getPreco() * quantidadePrato;
        }
        pedido.setTotalPrecoPedido(totalPrecoPedido);
        pedidoRepository.save(pedido);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String pedidoFeito(){
        return "cardapio_success";
    }


}
