package tn.esprit.spring.controllers;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entities.Joueur;
import tn.esprit.spring.service.IJoueurService;

import java.util.List;

@RestController
@RequestMapping("/joueur")
public class JoueurRestController {

    @Autowired
    IJoueurService iJoueurService;

    @GetMapping("/get-all-joueurs")
    @ResponseBody
    public List<Joueur> getJoueurs() {
        return  iJoueurService.getAllJoueurs() ;
    }


}
