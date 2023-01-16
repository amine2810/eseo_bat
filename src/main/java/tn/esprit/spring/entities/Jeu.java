package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String name;
    private String description;
    private int timer; // temps max pour toutes les questions en seconde
    private int score; // Score maximum atteignable du jeu
    @Enumerated(EnumType.STRING)
    private Section section;
    @Enumerated(EnumType.STRING)
    private TypeJeu typeJeu;

    @JsonIgnore
    @OneToMany(mappedBy = "jeu", cascade = CascadeType.ALL)
    private Set<JouerJeu> jouerJeuSet = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "jeu", cascade = CascadeType.REMOVE)
    private Set<Question> questions = new HashSet<>();

}
