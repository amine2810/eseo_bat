package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Reponse;

import java.util.List;
import java.util.Set;

public interface IReponseService {

    // GET
    Reponse getReponseById(Long id);
    List<Reponse> getReponsesByKeyword (String Keyword);
    List<Reponse> getReponsesVraies (Long idQuestion);
    List<Reponse> getReponsesFausses (Long idQuestion);
    Set<Reponse> getReponsesByQuestion (Long idQuestion);
    List<Reponse> getAllReponses ();

    // POST
    Reponse addReponse(Reponse reponse, Long idQuestion);

    // PUT
    Reponse updateReponse(Reponse reponse);

    // DELETE
    void removeReponse(Long id);
}
