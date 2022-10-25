package tn.esprit.spring.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    private String titre;
    private String contenu;
    private String QRCode;
    private Section section;

    @ManyToOne
    private  Admin admin;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "article")
    private Set<Multimedia> multimedias;


}
