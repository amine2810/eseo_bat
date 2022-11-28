package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Section;
import tn.esprit.spring.entities.TypeJeu;

import java.util.List;


public interface IJeuService {
    //Optional<Jeu> getJeu(final Long id);
    //Iterable<Jeu> getJeux();
    //void deleteJeu(final Long id);
    //Jeu saveJeu(Jeu jeu);

    // GET
    Jeu getJeuById(Long id);
    List<Jeu> getJeuByName (String name);
    List<Jeu> getJeuBySection (Section section);
    List<Jeu> getJeuByTypeJeu (TypeJeu typeJeu);
    List<Jeu> getAllJeuxByName();
    List<Jeu> getAllJeux();

    // POST
    Jeu addJeu(Jeu jeu);

    // PUT
    Jeu updateJeu(Jeu jeu);

    // DELETE
    void removeJeu(Long id);

}
