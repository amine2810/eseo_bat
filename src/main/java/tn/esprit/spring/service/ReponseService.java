package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Reponse;
import tn.esprit.spring.repository.JeuRepository;
import tn.esprit.spring.repository.QuestionRepository;
import tn.esprit.spring.repository.ReponseRepository;
import tn.esprit.spring.serviceInterface.IReponseService;

import java.util.List;
import java.util.Set;

@Service
public class ReponseService implements IReponseService {

    @Autowired
    ReponseRepository reponseRepository;

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionService questionService;


    // GET
    @Override
    public Reponse getReponseById(Long id) {
        return reponseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reponse> getReponsesByKeyword(String Keyword) {
        return reponseRepository.getReponsesByKeyword(Keyword);
    }

    @Override
    public List<Reponse> getReponsesVraies(Long idQuestion) {
        return reponseRepository.getReponsesVraies(idQuestion);
    }

    @Override
    public List<Reponse> getReponsesFausses(Long idQuestion) {
        return reponseRepository.getReponsesFausses(idQuestion);
    }


    @Override
    public Set<Reponse> getReponsesByQuestion(Long idQuestion) {
        Question question = questionRepository.getById(idQuestion);
        return question.getReponses();
    }

    @Override
    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }

    // POST
    @Override
    public Reponse addReponse(Reponse reponse, Long idQuestion) {
        Question question = questionService.getQuestionById(idQuestion);
        reponse.setQuestion(question);
        return reponseRepository.save(reponse);
    }

    // PUT
    @Override
    public Reponse updateReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    // DELETE
    @Override
    public void removeReponse(Long id) {
        reponseRepository.deleteById(id);
    }
}
