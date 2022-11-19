package tn.esprit.spring.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Joueur;
import tn.esprit.spring.repository.AdminRepository;
import tn.esprit.spring.repository.JoueurRepository;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Slf4j
public class JoueurServiceImpl implements IJoueurService  {

    @Autowired
    JoueurRepository joueurRepository;

    @Override
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    @Override
    public void removeJoueur(Long id) {
        joueurRepository.deleteById(id);
    }

    @Override
    public Joueur getJoueurById(Long id) {
        return joueurRepository.findById(id).orElse(null);
    }

    @Override
    public List<Joueur> getJoueurByName(String name) {
        return joueurRepository.getJoueurByName(name);
    }

    @Override
    public Joueur getJoueurByEmail(String email) {
        return joueurRepository.getJoueurByEmail(email);
    }

    @Override
    public Joueur addJoueur(Joueur joueur) {
        return joueurRepository.save(joueur);
    }

    @Override
    public Joueur updateJoueur(Joueur joueur) {
        return joueurRepository.save(joueur);
    }

    @Override
    public List<Joueur> getAllJoueursByScore() {
        return joueurRepository.getAllJoueursByScore();
    }

    @Override
    public List<Joueur> top10Joueurs() {
        Pageable topTen = (Pageable) PageRequest.of(0, 10);
        return joueurRepository.top10(topTen);
    }
}
