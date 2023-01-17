package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.Section;

import java.util.List;


@Repository
public interface ArticleRepository extends CrudRepository<Article,Long>{


    @Query("select a from Article a where  a.section=?1")
    public List<Article> getArticleBySection(Section section);

    @Modifying
    @Query("update  Article a set a.contenu=:contenu, a.titre=:titre where a.idArticle=:idArticle")
    public void mettreAjourArticle(@Param("contenu")String contenu, @Param("titre")String titre, @Param("idArticle") Long idArticle);

    @Modifying
    @Query("DELETE FROM Article a WHERE a.titre lIKE %:titre%")
    public void deleteArticleByTitre(@Param("titre") String titre );

    @Query("SELECT a FROM Article a WHERE a.titre LIKE %:titre% AND a.contenu LIKE %:contenu% ")
    Iterable<Article> searchByTitreAndContenu(@Param("titre") String titre, @Param("contenu") String contenu);


}
