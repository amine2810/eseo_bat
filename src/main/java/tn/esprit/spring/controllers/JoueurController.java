package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Section;
import tn.esprit.spring.serviceInterface.IJoueurService;
import tn.esprit.spring.entities.Joueur;

import java.util.Optional;

@RestController
public class JoueurController {

    @Autowired
    IJoueurService joueurService;

    /**
     * Create - Add a new Player
     * @param joueur An object joueur
     * @return The player object saved
     */
    @PostMapping("/joueur")
    public Joueur createJoueur(@RequestBody Joueur joueur) {
        return joueurService.saveJoueur(joueur);
    }

    /**
     *
     * @return An Iterable object of Joueurs full filled
     */
    @GetMapping("/joueurs")
    public Iterable<Joueur> getJoueurs() { return joueurService.getJoueurs(); }

    /**
     * @param id - the id of the player
     * @return A Player full filled
     */
    @GetMapping("/joueur/{id}")
    public Joueur getJoueur(@PathVariable("id") final long id){
        Optional<Joueur> joueur = joueurService.getJoueur(id);
        if (joueur.isPresent()){
            return joueur.get();
        }
        else{
            return null;
        }
    }

    /**
     *
     * @param id - the id of the player we want to modify
     * @param joueur - attributes of player we want to change
     * @return joueur - The updated player
     */
    @PutMapping("/joueur/{id}")
    public Joueur updateJoueur(@PathVariable("id") final long id, @RequestBody Joueur joueur){
        Optional<Joueur> j = joueurService.getJoueur(id);
        if(j.isPresent()) {
            Joueur currentPlayer = j.get();

            String nom = joueur.getNom();
            if (nom != null ){
                currentPlayer.setNom(nom);
            }

            String prenom = joueur.getPrenom();
            if (prenom != null ){
                currentPlayer.setPrenom(prenom);
            }

            String email = joueur.getEmail();
            if (email != null ){
                currentPlayer.setEmail(email);
            }

            String mdp = joueur.getMdp();
            if (mdp != null ){
                currentPlayer.setMdp(mdp);
            }

            int score = joueur.getScoreTotal();
            if (score >= 0 ){
                currentPlayer.setScoreTotal(score);
            }

            String imgDeProfil = joueur.getImgDeProfil();
            if (imgDeProfil != null ){
                currentPlayer.setImgDeProfil(imgDeProfil);
            }

            joueurService.saveJoueur(currentPlayer);
            return currentPlayer;
        }
        else{
            return null;
        }
    }


    /**
     * Delete - delete a player
     * @param id - The id of the player to delete
     */
    @DeleteMapping("/joueur/{id}")
    public void deleteJoueur(@PathVariable("id") final long id){
        joueurService.deleteJoueur(id);
    }

}
