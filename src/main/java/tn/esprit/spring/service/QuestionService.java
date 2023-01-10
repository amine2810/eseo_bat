package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Reponse;
import tn.esprit.spring.repository.JeuRepository;
import tn.esprit.spring.repository.QuestionRepository;
import tn.esprit.spring.repository.ReponseRepository;
import tn.esprit.spring.serviceInterface.IQuestionService;

import java.util.List;
import java.util.Set;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    JeuRepository jeuRepository;

    @Autowired
    ReponseRepository reponseRepository;


    // GET
    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> getQuestionsByKeyword(String question) {
        return questionRepository.getQuestionsByKeyword(question);
    }

    @Override
    public Question getQuestionByReponse(Long idReponse) {
        Reponse reponse = reponseRepository.getById(idReponse);
        return reponse.getQuestion();
    }

    @Override
    public Set<Question> getAllQuestionsByJeu(Long idJeu){
        Jeu jeu = jeuRepository.getById(idJeu);
        return jeu.getQuestions();
    }

    @Override
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    // POST
    @Override
    public Question addQuestion(Question question, Long idJeu) {
        Jeu jeu = jeuRepository.findById(idJeu).orElse(null);
        question.setJeu(jeu);
        return questionRepository.save(question);
    }

    // PUT
    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    // DELETE
    @Override
    public void removeQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
