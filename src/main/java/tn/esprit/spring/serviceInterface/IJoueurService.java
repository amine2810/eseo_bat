package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Joueur;

import java.util.Optional;

public interface IJoueurService {
    Optional<Joueur> getJoueur(final Long id);
    Iterable<Joueur> getJoueurs();
    void deleteJoueur(final Long id);
    Joueur saveJoueur(Joueur joueur);
}
