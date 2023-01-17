package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.JoueurJeu;
import tn.esprit.spring.serviceInterface.IJoueurJeuService;

@RestController
@RequestMapping("/joueurJeu")
public class JoueurJeuController {

    @Autowired
    IJoueurJeuService joueurJeuService;

    // GET

    /**
     * Read - Get the associated class JoueurJeu by id
     * @param idJoueurJeu The id of the associated class JoueurJeu
     * @return A Game object full filled with a score and an attempt counter
     */
    @GetMapping("/get-joueurJeu-byid/{id-JJ}")
    @ResponseBody
    public JoueurJeu getJoueurJeuById(@PathVariable("id-JJ") final long idJoueurJeu){
        return joueurJeuService.getJoueurJeuById(idJoueurJeu);
    }


    // POST
    /**
     * Create - Add a new JoueurJeu
     * @param joueurJeu An object joueurJeu composed of a Player and one Game
     * @param idJoueur The id of the Player
     * @param idJeu The id of the game played by the player
     * @return The JoueurJeu object saved
     */
    @PostMapping("/add-jeuToJoueur/{id-joueur}/{id-jeu}")
    @ResponseBody
    public JoueurJeu addJoueurJeu(@PathVariable("id-joueur") Long idJoueur, @PathVariable("id-jeu") Long idJeu, @RequestBody JoueurJeu joueurJeu) {
        return joueurJeuService.addJoueurJeu(idJoueur, idJeu, joueurJeu);
    }


    // PUT
    @PutMapping("/modify-joueurJeu/{id-JJ}")
    @ResponseBody
    public JoueurJeu updateJoueurJeu(@PathVariable("id-JJ") final Long idJoueurJeu ,@RequestBody JoueurJeu newJoueurJeu){
        JoueurJeu currentJoueurJeu = joueurJeuService.getJoueurJeuById(idJoueurJeu);

        if (currentJoueurJeu != null){
            int compteur = newJoueurJeu.getCompteur();
            if (compteur != 0) {
                currentJoueurJeu.setCompteur(compteur);
            }

            double score = newJoueurJeu.getScoreJoueur();
            if (score >= 0){

                // Si le joueur obtient un score au bout de la deuxième tentatives, ce score est diminué de 10 %
                if (currentJoueurJeu.getCompteur() == 2){
                    score = score * 0.9;
                }
                // Si le joueur obtient un score au bout de la troisième tentatives, ce score est diminué de 20 %
                else if(currentJoueurJeu.getCompteur() == 3){
                    score = score * 0.8;
                }
                // Le joueur a le droit a seulement 3 tentatives pour améliorer son score par jeu
                if(currentJoueurJeu.getCompteur() <= 3){
                    currentJoueurJeu.setScoreJoueur(score);
                }

            }

            joueurJeuService.updateJoueurJeu(currentJoueurJeu);

            return currentJoueurJeu;
        }
        else{
            return null;
        }
    }



    // DELETE
    /**
     * Delete - delete a JoueurJeu
     * @param idJoueurJeu - The id of the JoueurJeu to delete
     */
    @DeleteMapping("/remove-joueurJeu/{id-JJ}")
    @ResponseBody
    public void deleteJoueurJeu(@PathVariable("id-JJ") long idJoueurJeu){
        joueurJeuService.removeJoueurJeu(idJoueurJeu);
    }

}
