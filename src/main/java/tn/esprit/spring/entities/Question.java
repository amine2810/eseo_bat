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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestion;
    private String question;
    private String image; //ppour les quiz avec images

    @ManyToOne
    private  Jeu jeu;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question")
    private Set<Reponse> reponses;

}
