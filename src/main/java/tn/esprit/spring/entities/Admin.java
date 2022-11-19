package tn.esprit.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private  String nom;
    private  String prenom;
    private  String email;
    private  String mdp;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "admin")
    private Set<Article> articles;

}
