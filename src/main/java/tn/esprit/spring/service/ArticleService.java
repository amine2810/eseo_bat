package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.Multimedia;
import tn.esprit.spring.entities.Section;
import tn.esprit.spring.repository.ArticleRepository;
import tn.esprit.spring.repository.MultimediaRepository;
import tn.esprit.spring.serviceInterface.IArticleServices;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService implements IArticleServices {

    @Autowired
    ArticleRepository ar;

    @Autowired
    MultimediaRepository mr;



    @Override
    public Article addArticle(Article article) {
        return ar.save(article);
    }


    //@Override
   // public Iterable <Article> getAllArticles() {
      //  return ar.findAll();
   // }
    @Override
    public Article getArticle(Long idar) {
        // TODO Auto-generated method stub

        return ar.findById(idar).get();
    }

    @Override
    public void affecterArticleAMultimedia( Long artId,Long mulId) {
       Multimedia m = mr.findById(mulId).get();
       Article article = ar.findById(artId).get();
       m.setArticle(article);
       mr.save(m);
    }
    // afficher tout les articles avec leur multimedia
    @Override
    public ArrayList <String> getAllArticleByMultimedia() {

        //Article a=ar.findById(articleId).get();
        ArrayList<String> lien= new ArrayList<String>();
       // ArrayList<Article>articles=new ArrayList<Article>();
       // Set<Multimedia> mults=a.getMultimedias();
        Iterable<Article> arts=ar.findAll();

        for(Article article:arts){

            lien.add("Article "+article.getIdArticle()+":"+ "/n" +"Titre :"+(article.getTitre()) +
                    "Section :"+ (article.getSection())+
                    "\n"+(article.getContenu())+"\n"
                    +"QR Code : "+(article.getQRCode()));
            for(Multimedia multimedia: article.getMultimedias()){
            lien.add(multimedia.getLien());}
        }

        return lien;

    }


    @Override
    public Multimedia addArticleAndAssingnToMultimedia(Article article, Long multId) {

        Multimedia m = mr.findById(multId).get();
        m.setArticle(article);
        return mr.save(m);
    }

    // filtrer l'article selon section
    @Override
    public List<Article> getArticleBySection(Section section) {
        // TODO Auto-generated method stub
        return ar.getArticleBySection(section);
    }

    // mettre à jour l'article
    @Override
    public void mettreAjourArticle(String contenu, String titre, Long idArticle) {
        // TODO Auto-generated method stub
        ar.mettreAjourArticle(contenu,  titre,  idArticle);



    }
    // supprimer l'article selon titre
    @Override
    public void deleteArticleByTitre(String titre) {
        ar.deleteArticleByTitre(titre);
        // TODO Auto-generated method stub
    }


    // Rechercher les articles selon titre et contenu
    @Override
    public Iterable<String> searchByTitreAndContenu( String titre, String contenu )
    {
        //Iterable<Article> arts=ar.findAll();
        ArrayList<String> lien= new ArrayList<String>();
        Iterable<Article> arts_title_contenu=ar.searchByTitreAndContenu(titre,contenu);

        for(Article article:arts_title_contenu){

            lien.add("Article "+article.getIdArticle()+":"+ "/n" +"Titre :"+(article.getTitre()) +
                    "Section :"+ (article.getSection())+
                    "\n"+(article.getContenu())+"\n"
                    +"QR Code : "+(article.getQRCode()));
            for(Multimedia multimedia: article.getMultimedias()){
                lien.add(multimedia.getLien());}
        }

        return lien;




    }






}
