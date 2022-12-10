package tn.esprit.spring.service;

import tn.esprit.spring.entities.Admin;
import tn.esprit.spring.entities.Joueur;

import java.awt.print.Pageable;
import java.util.HashSet;
import java.util.List;

public interface IJoueurService {
    List<Joueur> getAllJoueurs();
    void removeJoueur(Long id);
    Joueur getJoueurById(Long id);
    List<Joueur> getJoueurByName(String name);
    Joueur getJoueurByEmail(String email);
    Joueur addJoueur(Joueur joueur);
    Joueur updateJoueur(Joueur joueur);
    List<Joueur> getAllJoueursByScore();
    List<Joueur> top10Joueurs();
}
