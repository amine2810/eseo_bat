package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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
    private String image; //pour les quiz avec images

    @JsonIgnore
    @ManyToOne
    private  Jeu jeu;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question")
    private Set<Reponse> reponses;

}
