package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.JoueurJeu;
import tn.esprit.spring.entities.Question;

import java.util.List;

@Repository
public interface JoueurJeuRepository extends JpaRepository<JoueurJeu, Long> {
}
