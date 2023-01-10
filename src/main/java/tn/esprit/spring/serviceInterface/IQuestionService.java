package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Question;

import java.util.List;
import java.util.Set;

public interface IQuestionService {

    // GET
    Question getQuestionById(Long id );

    List<Question> getQuestionsByKeyword(String keyword);

    Question getQuestionByReponse(Long idReponse);

    Set<Question> getAllQuestionsByJeu(Long idJeu);

    List<Question> getAllQuestions();

    // POST
    Question addQuestion(Question question, Long idJeu);

    // PUT
    Question updateQuestion(Question question);

    // DELETE
    void removeQuestion(Long id);

}
