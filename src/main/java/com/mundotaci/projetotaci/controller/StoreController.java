package com.mundotaci.projetotaci.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mundotaci.projetotaci.entities.Store;
import com.mundotaci.projetotaci.repository.StoreRepository;

@Controller
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/formulario")
    public String formularioLoja(Store store){
        return "addLoja";
    }

    @PostMapping("/adicionar")
    public String adicionarCadastro(@Valid Store store, BindingResult result){
        if(result.hasFieldErrors()){
            return "redirect:/formulario";
        }
        
        storeRepository.save(store);

        return "redirect:/index";
    }

    @GetMapping("/formulario/{storeId}")
    public String atualizarCadastro(Model model, @PathVariable(name="storeId") Long storeId){


        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Invalid person storeId:" + storeId));
        model.addAttribute("store", store);
        return "editarLoja";
    }

    // Atualiza Loja
    @PostMapping("atualizar/{storeId}")
    public String alterarProduto(@Valid Store store, BindingResult result, @PathVariable Long storeId) {

        if (result.hasErrors()) {
            return "redirect:/formulario";
        }
        
        storeRepository.save(store);
        return "redirect:/index";
    }

    @GetMapping("delete/{storeId}")
    public String delete(Model model, @PathVariable(name = "storeId") Long storeId) {

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + storeId));
        
        storeRepository.delete(store);
        return "redirect:/index";
    }
}
