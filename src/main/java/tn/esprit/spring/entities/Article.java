package tn.esprit.spring.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    private String titre;
    private String contenu;
    private String QRCode;
    @Enumerated(EnumType.STRING)
    private Section section;

    @JsonIgnore
    @ManyToOne
    private  Admin admin;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "article",orphanRemoval = true)
    private Set<Multimedia> multimedias;



}
