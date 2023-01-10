package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Reponse;

import java.util.List;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Long> {

    @Query(value="SELECT rep from Reponse rep where rep.reponse LIKE  %?1% ")
    public List<Reponse> getReponsesByKeyword (String Keyword);

    @Query(value = "SELECT rep from Reponse rep where rep.vrai=1 AND rep.question.id = ?1")
    public List<Reponse> getReponsesVraies (Long idQuestion);

    @Query(value="SELECT rep from Reponse rep where rep.vrai=0 AND rep.question.id = ?1")
    public List<Reponse> getReponsesFausses (Long idQuestion);


}
