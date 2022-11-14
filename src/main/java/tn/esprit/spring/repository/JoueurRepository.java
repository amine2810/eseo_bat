package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Joueur;

@Repository
public interface JoueurRepository extends CrudRepository<Joueur, Long> {
}
