package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Section;
import tn.esprit.spring.entities.TypeJeu;
import tn.esprit.spring.serviceInterface.IJeuService;
import tn.esprit.spring.serviceInterface.IQuestionService;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/jeu")
public class JeuController {

    @Autowired
    IJeuService jeuService;

    //GET

    /**
     * Read - Get one Jeu
     * @param id The id of the jeu
     * @return A Game object full filled
     */
    @GetMapping("/get-jeu-byid/{id}")
    @ResponseBody
    public Jeu getJeuById(@PathVariable("id") final long id){
        return jeuService.getJeuById(id);
    }

    /**
     * @param name The name of the jeu
     * @return List of games with name like the param
     */
    @GetMapping("/get-jeu-byname/{name}")
    @ResponseBody
    public List<Jeu> getJeuByName(@PathVariable("name") String name){
        return jeuService.getJeuByName(name);
    }

    /**
     * @param section The name of the section where the jeu is
     * @return List of games of a section
     */
    @GetMapping("/get-jeu-bysection/{section}")
    @ResponseBody
    public List<Jeu> getJeuBySection(@PathVariable("section") Section section){
        return jeuService.getJeuBySection(section);
    }

    /**
     * @param typeJeu the type of the jeu (enumeration)
     * @return List of games with the same type of jeu.
     */
    @GetMapping("/get-jeu-bytypejeu/{typeJeu}")
    @ResponseBody
    public List<Jeu> getJeuByTypeJeu(@PathVariable("typeJeu") TypeJeu typeJeu){
        return jeuService.getJeuByTypeJeu(typeJeu);
    }

    /**
     *
     * @return  A List with all the games sorted by name
     */
    @GetMapping("/get-jeux-byname")
    public List<Jeu> getJeuxByName(){ return jeuService.getAllJeuxByName(); }

    /**
     *
     * @return  A List with all the games
     */
    @GetMapping("/get-all-jeux")
    public List<Jeu> getJeux(){ return jeuService.getAllJeux(); }


    // POST
    /**
     * Create - Add a new Game
     * @param jeu An object jeu
     * @return The game object saved
     */
    @PostMapping("/add-jeu")
    @ResponseBody
    public Jeu addJeu(@RequestBody Jeu jeu) {
        return jeuService.addJeu(jeu);
    }

    // PUT

    @PutMapping("/modify-jeu/{id}")
    @ResponseBody
    public Jeu updateJeu(@PathVariable("id") final long id, @RequestBody Jeu jeu){
        Jeu currentGame = jeuService.getJeuById(id);
        if(currentGame != null ) {

            String nom = jeu.getName();
            if (nom != null ){
                currentGame.setName(nom);
            }

            String description = jeu.getDescription();
            if (description != null ){
                currentGame.setDescription(description);
            }

            int scoreMax = jeu.getScore();
            if (scoreMax >= 0 ){
                currentGame.setScore(scoreMax);
            }

            int timerMax = jeu.getTimer();
            if (timerMax >= 0 ){
                currentGame.setTimer(timerMax);
            }
            Section section = jeu.getSection();
            if (section != null ){
                currentGame.setSection(section);
            }

            TypeJeu typeJeu = jeu.getTypeJeu();
            if (typeJeu != null ){
                currentGame.setTypeJeu(typeJeu);
            }

            jeuService.updateJeu(currentGame);
            return currentGame;
        }
        else{
            return null;
        }
    }




    // DELETE
    /**
     * Delete - delete a game
     * @param jeuId - The id of the game to delete
     */
    @DeleteMapping("/remove-jeu/{jeu-id}")
    @ResponseBody
    public void deleteJeu(@PathVariable("jeu-id") long jeuId){
        jeuService.removeJeu(jeuId);
    }

}
