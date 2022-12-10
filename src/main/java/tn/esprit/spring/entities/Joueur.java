package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Joueur   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private  String nom;
    private  String prenom;
    private  String email;
    private  String mdp;
    private int score; //scroe total
    //private String img_profile;

    @JsonIgnore
    @OneToMany(mappedBy = "joueur", cascade = CascadeType.ALL)
    private Set<JouerJeu> jouerJeuSet = new HashSet<>();

}
