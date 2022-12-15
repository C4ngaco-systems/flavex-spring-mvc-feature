package com.mundotaci.projetotaci.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mundotaci.projetotaci.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mundotaci.projetotaci.entities.Store;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StoreRepository storeRepository;

    @RequestMapping(method = RequestMethod.GET,path = {"/","search"})
    public String search(Model model, String findParam){
        if(findParam != null){
            List<Store> storeList = storeRepository.findAllFilter(findParam);
            model.addAttribute("stories", storeList);
        }
        else{
            Query query = (Query) entityManager.createQuery("select s from Store s", Store.class);
            List<Store> storeList = query.getResultList();
            model.addAttribute("stories", storeList);
        }

        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Query query = (Query) entityManager.createQuery("select s from Store s", Store.class);

        List<Store> stories = query.getResultList();
        model.addAttribute("stories", stories);

        return "index";
    }

}
