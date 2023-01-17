package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.Multimedia;
import tn.esprit.spring.entities.Section;

import java.util.ArrayList;
import java.util.List;

public interface IArticleServices {
    public Article addArticle (Article article);
    //public Iterable <Article> getAllArticles();
    public Article getArticle(Long idar);
    public void affecterArticleAMultimedia( Long artId,Long mulId);
    public ArrayList<String> getAllArticleByMultimedia();
    public Multimedia addArticleAndAssingnToMultimedia(Article  article, Long multId);
    public List<Article> getArticleBySection(Section section);
    public void mettreAjourArticle(String contenu, String titre, Long idArticle);
    public void deleteArticleByTitre(String titre);
    public Iterable<String> searchByTitreAndContenu( String titre, String contenu );



}
