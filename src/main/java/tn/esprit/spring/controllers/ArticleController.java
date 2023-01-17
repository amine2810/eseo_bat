package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.Multimedia;
import tn.esprit.spring.entities.Section;
import tn.esprit.spring.serviceInterface.IArticleServices;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RestController
public class ArticleController {
    @Autowired
    IArticleServices ias;

    @PostMapping("/ajouterArticle")
    public Article ajouterArticle(@RequestBody Article article){
        return ias.addArticle(article);
    }


   // @GetMapping("/getArticle")
   // public Iterable <Article> getArticle() {
    //    return ias.getAllArticles();
    //}

    @GetMapping("/getArticleOnly/{id}")
    public  Article getArticle(@PathVariable("id")  Long id) {
        return ias.getArticle(id);
    }



    @PutMapping("/AffecterArticleToMultimedia/{ArticleId}/{MultimediaId}")
    public void affecterArticleAMultimedia(@PathVariable("ArticleId")Long articleId,@PathVariable("MultimediaId") Long multimediaId) {
        ias.affecterArticleAMultimedia(articleId,multimediaId);
    }

    @GetMapping("/getArticleByMultimedia")
    public ArrayList<String> getAllArticleByMultimedia(){
        return ias.getAllArticleByMultimedia();
    }
    @PutMapping("/ajouterArticleAvecMultimedia/{MultimediaId}")
    public Multimedia addArticleAndAssingnToMultimedia(@RequestBody Article article, @PathVariable("MultimediaId") Long multimediaId)
    {
        return ias.addArticleAndAssingnToMultimedia(article,multimediaId);
    }

    @GetMapping("/getArticleBySection/{section}")
    public List<Article> getArticlesBySection(@PathVariable("section") Section section) {
        return ias.getArticleBySection(section);
    }

    @GetMapping("/nbrArticleBySection/{section}")
    public int getNbrArticleWithSection(@PathVariable("section") Section section )
    {
        return ias.getArticleBySection(section).size();
    }

    @PutMapping("/UpdateArticle/{contenu}/{titre}/{idArticle}")
    public void mettreAjourArticle(@PathVariable("contenu")String contenu,@PathVariable("titre") String titre,@PathVariable("idArticle") Long idArticle){

        ias.mettreAjourArticle(contenu, titre, idArticle);


    }
    @DeleteMapping("/deleteArticle/{titre}")
    public void deleteArticleByTitre(@PathVariable("titre")String titre){
        ias.deleteArticleByTitre(titre);

    }

    @GetMapping("/SearchByTitreAndContenu")
    public Iterable<String> searchByTitreAndContenu( @RequestParam(value = "titre", required = false, defaultValue = "") String titre, @RequestParam(value = "contenu", required = false, defaultValue = "") String contenu )
    {
        return ias.searchByTitreAndContenu(titre,contenu);
    }


}
