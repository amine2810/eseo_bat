package tn.esprit.spring.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.repository.JeuRepository;
import tn.esprit.spring.serviceInterface.IJeuService;

import java.util.Optional;

@Data
@Service
public class JeuService implements IJeuService {
    @Autowired
    private JeuRepository jeuRepository;

    public Optional<Jeu> getJeu(final Long id) {
        return jeuRepository.findById(id);
    }

    public Iterable<Jeu> getJeux() {
        return jeuRepository.findAll();
    }

    public void deleteJeu(final Long id) {
        jeuRepository.deleteById(id);
    }

    public Jeu saveJeu(Jeu jeu) {
        Jeu saveJeu = jeuRepository.save(jeu);
        return saveJeu;
    }
}
