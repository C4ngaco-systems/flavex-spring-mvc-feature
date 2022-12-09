package com.mundotaci.projetotaci.controller;
import javax.validation.Valid;

import com.mundotaci.projetotaci.statics.Estado;
import com.mundotaci.projetotaci.statics.EstadosEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mundotaci.projetotaci.entities.Store;
import com.mundotaci.projetotaci.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    private List<Estado> estadosDiponiveis;
    StoreController() {
        estadosDiponiveis = getEstados();
    }

    @GetMapping("/formulario")
    public String formularioLoja(Model model, Store store){

        model.addAttribute("estados", estadosDiponiveis);
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
        int stateId = getEstadoId(store.getState());

        model.addAttribute("store", store);
        model.addAttribute("estados", estadosDiponiveis);
        model.addAttribute("stateId", stateId);

                
        return "editarLoja";
    }

    private int getEstadoId(String state) {
        int selectedId = 0;
        for(int i=0; i < estadosDiponiveis.size(); i++) {
            if (estadosDiponiveis.get(i).nome.equals(state)) {
                selectedId = i;
            }
        }
        return selectedId;
    }

    public List<Estado> getEstados() {
        String[] estadosString = Stream.of(EstadosEnum.values()).map(Enum::name).toArray(String[]::new);
        List<Estado> estados = new ArrayList<>();
        for (int i=0; i < estadosString.length; i++) {
            estados.add(new Estado(i, estadosString[i]));
        }
        return estados;
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
