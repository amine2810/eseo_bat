package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Reponse;
import tn.esprit.spring.serviceInterface.IQuestionService;
import tn.esprit.spring.serviceInterface.IReponseService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/reponse")
public class ReponseController {

    @Autowired
    IReponseService reponseService;

    @Autowired
    IQuestionService questionService;

    // GET

    /**
     * Read - Get one Reponse
     * @param id The id of the reponse
     * @return A Reponse object full filled
     */
    @GetMapping("/get-reponse-byid/{id}")
    //@ResponseBody
    public Reponse getReponseById(@PathVariable("id") final long id){
        return reponseService.getReponseById(id);
    }


    /**
     * Read - Get one or several Reponse
     * @param reponse The string word of the Reponse object
     * @return A List of Reponse object full filled
     */
    @GetMapping("/get-reponse-byreponse/{reponse}")
    @ResponseBody
    public List<Reponse> getReponsesByKeyword(@PathVariable("reponse") final String reponse){
        return reponseService.getReponsesByKeyword(reponse);
    }

    /**
     * @param idQuestion id of the question
     * @return List<Reponse> list of true reponse
     */
    @GetMapping("/get-all-reponses-true-byquestion/{idQuestion}")
    public List<Reponse> getReponsesVraies(@PathVariable("idQuestion") Long idQuestion){
        return reponseService.getReponsesVraies(idQuestion);
    }

    /**
     * @param idQuestion id of the question
     * @return List<Reponse> list of false reponse
     */
    @GetMapping("/get-all-reponses-false-byquestion/{idQuestion}")
    public List<Reponse> getReponsesFausses(@PathVariable("idQuestion") Long idQuestion){
        return reponseService.getReponsesFausses(idQuestion);
    }

    /**
     * Read - Get all the reponses of one question
     * @param idQuestion - id of the question
     * @return List<Reponse> - list of reponse by question
     */
    @GetMapping("/get-all-reponses-byquestion/{idQuestion}")
    public Set<Reponse> getReponsesByQuestion(@PathVariable("idQuestion") Long idQuestion){
        return reponseService.getReponsesByQuestion(idQuestion);
    }


    /**
     * Read - Get all the reponses of all the games
     * @return List<Reponse>
     */
    @GetMapping("/get-all-reponses")
    public List<Reponse> getAllReponses(){
        return reponseService.getAllReponses();
    }


    // POST

    /**
     * Create - Add a new Reponse
     * @param reponse An object Reponse
     * @param idQuestion the id of the question containing this reponse
     * @return The reponse object saved
     */
    @PostMapping("/add-reponse/{id-question}")
    @ResponseBody
    public Reponse addReponse(@PathVariable("id-question") Long idQuestion, @RequestBody Reponse reponse) {
        return reponseService.addReponse(reponse, idQuestion);
    }

    // PUT

    /**
     * Change reponse assignment for another question
     * @param idReponse
     * @param idQuestion
     */
    @PutMapping("/change-question/{idReponse}/{idQuestion}")
    public void changeQuestionOfTheReponse(@PathVariable("idReponse") Long idReponse, @PathVariable("idQuestion") Long idQuestion){
        Reponse reponse = reponseService.getReponseById(idReponse);
        Question question = questionService.getQuestionById(idQuestion);
        reponse.setQuestion(question);
        reponseService.updateReponse(reponse);
    }

    @PutMapping("/modify-reponse/{idReponse}")
    @ResponseBody
    public Reponse updateReponse(@PathVariable("idReponse") final Long idReponse ,@RequestBody Reponse newReponse){
        Reponse currentReponse = reponseService.getReponseById(idReponse);

        if (currentReponse != null){

            String reponseString = newReponse.getReponse();
            if (reponseString != null) {
                currentReponse.setReponse(reponseString);
            }

            int vrai = newReponse.getVrai();
            if (vrai == 0 || vrai == 1){
                currentReponse.setVrai(vrai);
            }

            Question question = newReponse.getQuestion();
            if (question != null){
                currentReponse.setQuestion(question);
            }

            reponseService.updateReponse(currentReponse);
            return currentReponse;
        }
        else{
            return null;
        }
    }

    // DELETE

    /**
     * Delete - delete a Reponse
     * @param idReponse - The id of the Reponse to delete
     */
    @DeleteMapping("/remove-reponse/{reponse-id}")
    @ResponseBody
    public void deleteReponse(@PathVariable("reponse-id") long idReponse){
        reponseService.removeReponse(idReponse);
    }

}
