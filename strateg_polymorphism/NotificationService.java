package strateg_polymorphism;

import com.example.Backend.Services.Strategy.NotificationStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService {
    private NotificationStrategy notificationStrategy;

    // Injection de stratégie alternative si besoin
    public void setStrategy(NotificationStrategy strategy) {
        this.notificationStrategy = strategy;
    }

    public void sendNotification(String title, String body, String target) {
        notificationStrategy.send(title, body, target);
    }
}
