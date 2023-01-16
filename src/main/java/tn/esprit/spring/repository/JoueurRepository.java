package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Joueur;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Joueur;


import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Long> {

    @Query(value = "SELECT  j  from  Joueur j where j.nom  LIKE  %?1%  or j.prenom  LIKE  %?1% ")
    public List<Joueur> getJoueurByName (String name);

    @Query(value = "SELECT  j  from  Joueur j where j.email  LIKE  ?1 ")
    public Joueur getJoueurByEmail (String email);

    @Query(value = "SELECT  j  from  Joueur j order by j.score DESC  ")
    public List<Joueur> getAllJoueursByScore ();
}
