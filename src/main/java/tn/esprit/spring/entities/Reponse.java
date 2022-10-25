package tn.esprit.spring.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReponse;

    private  String reponse;
    private int vrai ; //   ( 0 : fausse )  , ( 1 : bonne reponse)
    @ManyToOne
    private  Question question;

}
