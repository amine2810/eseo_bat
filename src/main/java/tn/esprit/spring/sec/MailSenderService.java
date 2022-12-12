package tn.esprit.spring.sec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender emailSender;


    /*public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
*/
    public  void   sendEmail(String to, String subject, String message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(message);
        msg.setFrom("eseo.bat@gmail.com");
        emailSender.send(msg);
        System.out.println("msg envoyé");
    }


}
