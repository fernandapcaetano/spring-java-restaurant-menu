package com.restaurante.restaurante.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.restaurante.restaurante.model.Pedido;
import com.restaurante.restaurante.model.Prato;
import com.restaurante.restaurante.repository.PedidoRepository;
import com.restaurante.restaurante.repository.PratoRepository;
import com.restaurante.restaurante.util.UploadUtil;

@Controller
public class AdministradosController {

    @Autowired
    private PratoRepository pratoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UploadUtil uploadUtil;

    @GetMapping("/administrador")
    public String administrador(Model model) {
        model.addAttribute("pratos", pratoRepository.findAll());
        return "administrador";
    }

    @GetMapping("/administrador/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de prato inválido: " + id));
        model.addAttribute("prato", prato);
        return "editar_prato";
    }

    @PostMapping("/administrador/editar/{id}")
    public ModelAndView editarPrato(@ModelAttribute Prato prato, @RequestParam("foto") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("redirect:/administrador");

        try {
            if (imagem != null && !imagem.isEmpty()) {
                if (uploadUtil.fazerUploadImagem(imagem)) {
                    prato.setFotoCaminho(imagem.getOriginalFilename());
                } else {
                    mv.addObject("msgErro", "Falha ao fazer upload da imagem.");
                    mv.setViewName("editar_prato");
                    return mv;
                }
            }

            pratoRepository.save(prato);

        } catch (Exception e) {
            mv.addObject("msgErro", "Erro ao editar o prato: " + e.getMessage());
            mv.setViewName("editar_prato");
            return mv;
        }

        return mv;
    }

    @GetMapping("/administrador/excluir/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de prato inválido: " + id));
        model.addAttribute("prato", prato);
        return "excluir_prato";
    }

    @PostMapping("/administrador/excluir/{id}")
    public String excluirPrato(@PathVariable Long id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de prato inválido: " + id));
        pratoRepository.delete(prato);
        return "redirect:/administrador";
    }

    @GetMapping("/administrador/historico")
    public String historico(Model model) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "historico";
    }

    @GetMapping("/administrador/add-novo-prato")
    public String addNovoPrato(Model model, Prato prato) {
        model.addAttribute("prato", prato);
        return "add_novo_prato";
    }
    @PostMapping("/administrador/add-novo-prato")
    public ModelAndView addNovoPrato(@ModelAttribute Prato prato, @RequestParam("foto") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("redirect:/administrador");

        try {
            if (imagem != null && !imagem.isEmpty()) {
                if (uploadUtil.fazerUploadImagem(imagem)) {
                    prato.setFotoCaminho(imagem.getOriginalFilename());
                } else {
                    mv.addObject("msgErro", "Falha ao fazer upload da imagem.");
                    mv.setViewName("adicionar_prato");
                    return mv;
                }
            }

            pratoRepository.save(prato);

        } catch (Exception e) {
            mv.addObject("msgErro", "Erro ao adicionar o prato: " + e.getMessage());
            mv.setViewName("adicionar_prato");
            return mv;
        }

        return mv;
    }

}
