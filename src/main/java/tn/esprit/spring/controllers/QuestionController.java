package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Reponse;
import tn.esprit.spring.serviceInterface.IJeuService;
import tn.esprit.spring.serviceInterface.IQuestionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    IQuestionService questionService;
    @Autowired
    IJeuService jeuService;

    //GET

    /**
     * Read - Get one Question
     * @param id The id of the question
     * @return A Question object full filled
     */
    @GetMapping("/get-question-byid/{id}")
    @ResponseBody
    public Question getQuestionById(@PathVariable("id") final long id){
        return questionService.getQuestionById(id);
    }

    /**
     * Read - Get one or several Question
     * @param question The string question of the Question object
     * @return A List of Question object full filled
     */
    @GetMapping("/get-question-byquestion/{question}")
    @ResponseBody
    public List<Question> getQuestionsByKeyword(@PathVariable("question") final String question){
        return questionService.getQuestionsByKeyword(question);
    }

    /**
     * Read - Get all the questions of one game
     * @param idJeu - id of a game
     * @return List<Question> - list of question of the wanted game
     */
    @GetMapping("/get-all-questions-bygame/{idJeu}")
    public Set<Question> getAllQuestionsByJeu(@PathVariable("idJeu") Long idJeu){
        return questionService.getAllQuestionsByJeu(idJeu);
    }


    @GetMapping("/get-question-byreponse/{idReponse}")
    public Question getQuestionByReponse(@PathVariable("idReponse") Long idReponse){
        return questionService.getQuestionByReponse(idReponse);
    }


    /**
     * Read - Get all the questions of all the games
     * @return List<Question>
     */
    @GetMapping("/get-all-questions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }


    // POST
    /**
     * Create - Add a new Question
     * @param question An object Question
     * @param idJeu The id of the game containing the question
     * @return The question object saved
     */
    @PostMapping("/add-question/{id-jeu}")
    @ResponseBody
    public Question addQuestion(@PathVariable("id-jeu") Long idJeu, @RequestBody Question question) {
        return questionService.addQuestion(question, idJeu);
    }

    // PUT

    /**
     * Change question assignment for another game
     * @param idQuestion
     * @param idJeu
     */
    @PutMapping("/change-jeu/{idQuestion}/{idJeu}")
    public void changeGameOfTheQuestion(@PathVariable("idQuestion") Long idQuestion, @PathVariable("idJeu") Long idJeu){
        Question question = questionService.getQuestionById(idQuestion);
        Jeu jeu = jeuService.getJeuById(idJeu);
        question.setJeu(jeu);
        questionService.updateQuestion(question);
    }

    @PutMapping("/modify-question/{idQuestion}")
    @ResponseBody
    public Question updateQuestion(@PathVariable("idQuestion") final Long idQuestion ,@RequestBody Question newQuestion){
        Question currentQuestion = questionService.getQuestionById(idQuestion);

        if (currentQuestion != null){
            String questionString = newQuestion.getQuestion();
            if (questionString != null) {
                System.out.println("---------quest------");
                currentQuestion.setQuestion(questionString);
            }

            String image = newQuestion.getImage();
            if (image != null){
                currentQuestion.setImage(image);
            }

            questionService.updateQuestion(currentQuestion);

            return currentQuestion;
        }
        else{
            return null;
        }
    }

    // DELETE
    /**
     * Delete - delete a Question
     * @param questionId - The id of the Question to delete
     */
    @DeleteMapping("/remove-jeu/{question-id}")
    @ResponseBody
    public void deleteQuestion(@PathVariable("question-id") long questionId){
        questionService.removeQuestion(questionId);
    }

}
