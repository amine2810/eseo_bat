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
public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJeu;
    private int timer; // temps max pour tous les questions
    private int score;
    private Section section;


    @OneToMany(mappedBy = "jeu", cascade = CascadeType.ALL)
    private Set<JouerJeu> jouerJeuSet = new HashSet<>();

    @OneToMany(mappedBy = "jeu", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

}
