package tn.esprit.spring.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Section;
import tn.esprit.spring.entities.TypeJeu;
import tn.esprit.spring.repository.JeuRepository;
import tn.esprit.spring.serviceInterface.IJeuService;

import java.util.List;

@Data
@Service
public class JeuService implements IJeuService {
    @Autowired
    private JeuRepository jeuRepository;

    @Autowired
    private QuestionService questionService;

    // GET
    @Override
    public Jeu getJeuById(Long id){
        return jeuRepository.findById(id).orElse(null);
    }
    @Override
    public List<Jeu> getJeuByName (String name){
        return jeuRepository.getJeuByName(name);
    }
    @Override
    public List<Jeu> getJeuBySection (Section section){
        return jeuRepository.getJeuBySection(section);
    }
    @Override
    public List<Jeu> getJeuByTypeJeu (TypeJeu typeJeu){
        return jeuRepository.getJeuByTypeJeu(typeJeu);
    }
    @Override
    public List<Jeu> getAllJeuxByName (){
        return jeuRepository.getAllJeuxByName();
    }
    @Override
    public List<Jeu> getAllJeux() {
        return jeuRepository.findAll();
    }

    // POST
    @Override
    public Jeu addJeu(Jeu jeu){
        return jeuRepository.save(jeu);
    }

    // PUT
    @Override
    public Jeu updateJeu(Jeu jeu){
        return jeuRepository.save(jeu);
    }

    // DELETE
    @Override
    public void removeJeu(Long id){
        jeuRepository.deleteById(id);
    }
}
