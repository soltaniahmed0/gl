package strategy;

import org.springframework.stereotype.Component;

@Component("email") // bean name will be "email"
public class EmailNotificationStrategy implements NotificationStrategy {

    @Override
    public void send(String title, String body, String emailAddress) {
        // Simulated email send
        System.out.println("Sending email to " + emailAddress);
        System.out.println("Subject: " + title);
        System.out.println("Body: " + body);
    }
}
