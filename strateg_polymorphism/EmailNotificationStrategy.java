package strateg_polymorphism;

import org.springframework.stereotype.Component;

package strateg_polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component("email")
public class EmailNotificationStrategy implements NotificationStrategy {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String title, String body, String targetEmail) {
        if (javaMailSender == null) {
            throw new IllegalStateException("JavaMailSender is not initialized.");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(targetEmail);
        message.setSubject(title);
        message.setText(body);
        javaMailSender.send(message);
    }
}
