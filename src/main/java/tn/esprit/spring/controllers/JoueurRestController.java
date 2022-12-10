package tn.esprit.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Requests.ChangePWDRequest;
import tn.esprit.spring.Requests.LoginRequest;
import tn.esprit.spring.Requests.ResetPWDRequest;
import tn.esprit.spring.entities.Admin;
import tn.esprit.spring.entities.Joueur;
import tn.esprit.spring.sec.MailSenderService;
import tn.esprit.spring.service.GeneratorService;
import tn.esprit.spring.service.IJoueurService;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/joueur")
public class JoueurRestController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    IJoueurService iJoueurService;
    @Autowired
    MailSenderService mailSender;
    @Autowired
    GeneratorService generator;
   // BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();


    @GetMapping("/get-all-joueurs")
    @ResponseBody
    public List<Joueur> getJoueurs() {
        return  iJoueurService.getAllJoueurs() ;
    }

    @GetMapping("/get-joueur-byid/{id}")
    @ResponseBody
    public Joueur getjoueurById(@PathVariable("id") Long id){return iJoueurService.getJoueurById(id);}

    @GetMapping("/get-joueur-byname/{name}")
    @ResponseBody
    public List<Joueur> getJoueurByName(@PathVariable("name") String name) { return  iJoueurService.getJoueurByName(name);}

    @GetMapping("/get-joueur-byemail/{email}")
    @ResponseBody
    Joueur getJoueurByEmail(@PathVariable("email") String email) { return iJoueurService.getJoueurByEmail(email); }

    @GetMapping("/get-joueurs-byscore")
    @ResponseBody
    public List<Joueur> getJoueursByScore() {
        return  iJoueurService.getAllJoueursByScore() ;
    }

    @GetMapping("/get-top10")
    @ResponseBody
    public List<Joueur> top10Joueurs() {

        return  iJoueurService.top10Joueurs() ;
    }

    @DeleteMapping("/remove-joueur/{joueur-id}")
    @ResponseBody
    public void removeJoueur(@PathVariable("joueur-id") Long joueurid) {
        iJoueurService.removeJoueur(joueurid);}


    @PostMapping("/add-joueur")
    //@ResponseBody
    public String addJoueur (@RequestBody Joueur joueur)
    {   String msg="";
        Joueur userExists = iJoueurService.getJoueurByEmail(joueur.getEmail());
        if (userExists != null) {
            msg="There is already a user registered with the email provided";
        } else {
            Joueur j = iJoueurService.addJoueur(joueur);
            msg="Account created successfully";
        }
        return msg;
    }


    /*@PutMapping("/modify-joueur")
    @ResponseBody
    public Joueur updateJoueur(@RequestBody Joueur joueur) {
        return iJoueurService.updateJoueur(joueur);
    }*/


    /*@ResponseBody
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Joueur user = new Joueur();
        user.setEmail("");

        user.setEmail(iJoueurService.getJoueurByEmail(loginRequest.getUsername()).getEmail());

        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!user.getEmail().isEmpty()) {
            if (loginRequest.getPassword().matches(user.getMdp())) {
                return ResponseEntity.ok(user);
            }
        }
        Joueur user1 = new Joueur();
        return ResponseEntity.ok(user1);
    }*/

    @PutMapping("/modify-joueur/{id}")
    @ResponseBody
    public Joueur updateJoueur(@RequestBody Joueur user , @PathVariable("id") long userid) {
        Joueur user1 = iJoueurService.getJoueurById(userid);
        if( user.getEmail().isEmpty())
            user.setEmail(user1.getEmail());
        if( user.getNom().isEmpty())
            user.setNom(user1.getNom());
        if( user.getPrenom().isEmpty())
            user.setPrenom(user1.getPrenom());
        if( user.getEmail().isEmpty())
            user.setEmail(user1.getEmail());

        user.setIdUser(userid);

        return iJoueurService.updateJoueur(user);
    }

    @ResponseBody
    @PostMapping("/signin")
    public Joueur authenticateUser(@RequestBody LoginRequest loginRequest) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Joueur userExists = iJoueurService.getJoueurByEmail(loginRequest.getUsername());
        if (userExists != null) {
            if (encoder.matches(loginRequest.getPassword(), userExists.getMdp())) {
                return (userExists);
            }
        }
        return new Joueur();
    }

    @PutMapping("/modify-pwd-joueur/{id}")
    @ResponseBody
    public ResponseEntity<?> updatePWD(@RequestBody ChangePWDRequest changePWDRequest , @PathVariable("id") long userid) {
        Joueur user = iJoueurService.getJoueurById(userid);

        String old_entred=changePWDRequest.getOldPassword();
        String old_db= user.getMdp();
        String entred= changePWDRequest.getNewPassword();
        if (! (entred).matches(old_entred)){
            return ResponseEntity
                    .badRequest()
                    .body(("Error: Ancien mot de passe est incorrect"));}
        else{
            user.setMdp((entred));
            iJoueurService.addJoueur(user);
            return ResponseEntity.ok().body(("Mot de passe changé avec succés"));
        }
    }

    @PutMapping("/reset_password")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> reset_password( @RequestBody ResetPWDRequest resetPWDRequest){
        Joueur joueur = iJoueurService.getJoueurByEmail(resetPWDRequest.getUsername());
        if (joueur != null)
            return ResponseEntity.notFound().build();

        String pwd=generator.Pwdgenerator();
        joueur.setMdp(encoder.encode(pwd));
        mailSender.sendEmail(resetPWDRequest.getUsername(),"Récupérer mot de passe","Bonjour Mme/Mr "+joueur.getNom()
                +" \n Vous avez oublié votre mot de passe, la connexion est maintenant disponible avec  le nouveau mot de passe ci-dessous . \n"
                +"\n Vous pouvez le changer pour plus de sécurité. \n"
                +pwd
                +"\n \n Merci");
        iJoueurService.addJoueur(joueur);
        return ResponseEntity.ok().body(("Mot de passe récupéré avec succés"));
    }

}
