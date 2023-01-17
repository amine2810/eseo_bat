package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.JoueurJeu;

public interface IJoueurJeuService {

    // GET
    JoueurJeu getJoueurJeuById(Long idJoueurJeu);

    // POST
    JoueurJeu addJoueurJeu(Long idJoueur, Long idJeu, JoueurJeu joueurJeu);

    // PUT
    JoueurJeu updateJoueurJeu(JoueurJeu joueurJeu);

    // DELETE
    void removeJoueurJeu(Long idJoueurJeu);
}
