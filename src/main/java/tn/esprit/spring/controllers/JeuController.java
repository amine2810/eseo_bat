package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Section;
import tn.esprit.spring.entities.Type_jeu;
import tn.esprit.spring.serviceInterface.IJeuService;

import java.util.Optional;

@RestController
public class JeuController {

    @Autowired
    IJeuService jeuService;


    /**
     * Create - Add a new Game
     * @param jeu An object jeu
     * @return The game object saved
     */
    @PostMapping("/jeu")
    public Jeu createJeu(@RequestBody Jeu jeu) {
        return jeuService.saveJeu(jeu);
    }

    /**
     *
     * @return  An Iterable object of Jeu full filled
     */
    @GetMapping("/jeux")
    public Iterable<Jeu> getJeux(){
        return jeuService.getJeux();
    }

    /**
     * Read - Get one Jeu
     * @param id The id of the jeu
     * @return A Game object full filled
     */
    @GetMapping("/jeu/{id}")
    public Jeu getJeu(@PathVariable("id") final long id){
        Optional<Jeu> jeu = jeuService.getJeu(id);
        if(jeu.isPresent()){
            return jeu.get();
        }
        else{
            return null;
        }
    }

    @PutMapping("/jeu/{id}")
    public Jeu updateJeu(@PathVariable("id") final long id, @RequestBody Jeu jeu){
        Optional<Jeu> j = jeuService.getJeu(id);
        if(j.isPresent()) {
            Jeu currentGame = j.get();

            String name = jeu.getName();
            if (name != null ){
                currentGame.setName(name);
            }
            String description = jeu.getDescription();
            if (description != null ){
                currentGame.setDescription(description);
            }

            int timer = jeu.getTimer();
            if (timer > 0 ){
                currentGame.setTimer(timer);
            }

            int score = jeu.getScore();
            if (score >= 0 ){
                currentGame.setScore(score);
            }
            Section section = jeu.getSection();
            if (section != null ){
                currentGame.setSection(section);
            }
            Type_jeu typeJeu = jeu.getTypeJeu();
            if (typeJeu != null ){
                currentGame.setTypeJeu(typeJeu);
            }

            jeuService.saveJeu(currentGame);
            return currentGame;
        }
        else{
            return null;
        }
    }

    /**
     * Delete - delete a game
     * @param id - The id of the game to delete
     */
    @DeleteMapping("/jeu/{id}")
    public void deleteJeu(@PathVariable("id") final long id){
        jeuService.deleteJeu(id);
    }

}
