package tn.esprit.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Joueur  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private  String nom;
    private  String prenom;
    private  String email;
    private  String mdp;
    private int scoreTotal; //score total
    //private String imgDeProfil;

    @OneToMany(mappedBy = "joueur", cascade = CascadeType.ALL)
    private Set<JoueurJeu> joueurJeuSet = new HashSet<>();
}
