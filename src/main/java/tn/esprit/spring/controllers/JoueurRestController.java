package tn.esprit.spring.controllers;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Admin;
import tn.esprit.spring.entities.Joueur;
import tn.esprit.spring.service.IJoueurService;

import java.awt.print.Pageable;
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

    @GetMapping("/get-joueur-byid/{id}")
    @ResponseBody
    public Joueur getjoueurById(@PathVariable("id") Long id){return iJoueurService.getJoueurById(id);}

    @GetMapping("/get-joueur-byname/{name}")
    @ResponseBody
    public List<Joueur> getJoueurByName(@PathVariable("name") String name) { return  iJoueurService.getJoueurByName(name);}

    @GetMapping("/get-joueur-byemail/{email}")
    @ResponseBody
    Joueur getJoueurByEmail(@PathVariable("email") String email) { return iJoueurService.getJoueurByEmail(email); }

    @GetMapping("/get-joueurs-byscore")
    @ResponseBody
    public List<Joueur> getJoueursByScore() {
        return  iJoueurService.getAllJoueursByScore() ;
    }
    @GetMapping("/get-top10")
    @ResponseBody
    public List<Joueur> top10Joueurs() {
        return  iJoueurService.top10Joueurs() ;
    }

    @DeleteMapping("/remove-joueur/{joueur-id}")
    @ResponseBody
    public void removeJoueur(@PathVariable("joueur-id") Long joueurid) {
        iJoueurService.removeJoueur(joueurid);}

    @PostMapping("/add-joueur")
    @ResponseBody
    public Joueur addJoueur (@RequestBody Joueur joueur)
    {
        Joueur j = iJoueurService.addJoueur(joueur);
        return j;
    }


    @PutMapping("/modify-joueur")
    @ResponseBody
    public Joueur updateJoueur(@RequestBody Joueur joueur) {
        return iJoueurService.updateJoueur(joueur);
    }



}
