package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Section;
import tn.esprit.spring.entities.TypeJeu;

import java.util.List;

@Repository
public interface JeuRepository extends JpaRepository<Jeu,Long> {

    @Query(value = "SELECT  jeu  from  Jeu jeu where jeu.name LIKE  %?1% ")
    public List<Jeu> getJeuByName (String name);

    @Query(value = "SELECT  jeu  from  Jeu jeu where jeu.section LIKE  %?1% ")
    public List<Jeu> getJeuBySection (Section section);

    @Query(value = "SELECT  jeu  from  Jeu jeu where jeu.typeJeu LIKE  %?1% ")
    public List<Jeu> getJeuByTypeJeu (TypeJeu typeJeu);

    @Query(value = "SELECT  jeu  from  Jeu jeu order by jeu.name ASC  ")
    public List<Jeu> getAllJoueursByName ();



}
