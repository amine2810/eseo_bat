package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Joueur;
import tn.esprit.spring.repository.JoueurRepository;
import tn.esprit.spring.serviceInterface.IJoueurService;

import java.util.Optional;

@Service
public class JoueurService implements IJoueurService {

    @Autowired
    JoueurRepository JoueurRepository;

    public Optional<Joueur> getJoueur(final Long id) {
        return JoueurRepository.findById(id);
    }

    public Iterable<Joueur> getJoueurs() {
        return JoueurRepository.findAll();
    }

    public void deleteJoueur(final Long id) {
        JoueurRepository.deleteById(id);
    }

    public Joueur saveJoueur(Joueur joueur) {
        Joueur saveJoueur = JoueurRepository.save(joueur);
        return saveJoueur;
    }
}
