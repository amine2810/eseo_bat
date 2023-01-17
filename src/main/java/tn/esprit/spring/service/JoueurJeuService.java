package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Joueur;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.JoueurJeu;
import tn.esprit.spring.repository.JeuRepository;
import tn.esprit.spring.repository.JoueurJeuRepository;
import tn.esprit.spring.repository.JoueurRepository;
import tn.esprit.spring.serviceInterface.IJoueurJeuService;

@Service
public class JoueurJeuService implements IJoueurJeuService {

    @Autowired
    JoueurJeuRepository joueurJeuRepository;

    @Autowired
    JeuRepository jeuRepository;

    @Autowired
    JoueurRepository joueurRepository;

    @Override
    public JoueurJeu getJoueurJeuById(Long idJoueurJeu) {
        return joueurJeuRepository.findById(idJoueurJeu).orElse(null);
    }

    @Override
    public JoueurJeu addJoueurJeu(Long idJoueur, Long idJeu, JoueurJeu joueurJeu) {
        Joueur joueur = joueurRepository.findById(idJoueur).orElse(null);
        Jeu jeu = jeuRepository.findById(idJeu).orElse(null);
        joueurJeu.setJoueur(joueur);
        joueurJeu.setJeu(jeu);
        return joueurJeuRepository.save(joueurJeu);
    }

    @Override
    public JoueurJeu updateJoueurJeu(JoueurJeu joueurJeu) {
        return joueurJeuRepository.save(joueurJeu);
    }

    @Override
    public void removeJoueurJeu(Long idJoueurJeu) {
        joueurJeuRepository.deleteById(idJoueurJeu);
    }
}
