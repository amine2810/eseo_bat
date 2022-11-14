package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Jeu;

import java.util.Optional;

public interface IJeuService {
    Optional<Jeu> getJeu(final Long id);
    Iterable<Jeu> getJeux();
    void deleteJeu(final Long id);
    Jeu saveJeu(Jeu jeu);
}
