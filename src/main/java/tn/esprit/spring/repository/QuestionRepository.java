package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Jeu;
import tn.esprit.spring.entities.Question;

import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value = "SELECT  quest  from  Question quest where quest.question LIKE  %?1% ")
    public List<Question> getQuestionsByKeyword (String quest);
}
