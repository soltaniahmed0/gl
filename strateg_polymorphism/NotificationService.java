package strategy;

import com.example.Backend.Services.Strategy.NotificationStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService {

    private final Map<String, NotificationStrategy> strategies;

    public NotificationService(Map<String, NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    public ResponseEntity<String> sendNotification(String type, String title, String body, String target) {
        NotificationStrategy strategy = strategies.get(type.toLowerCase());

        if (strategy == null) {
            return ResponseEntity.badRequest().body("Unsupported notification type: " + type);
        }

        try {
            strategy.send(title, body, target);
            return ResponseEntity.ok("Notification sent using " + strategy.getClass().getSimpleName());
        } catch (Exception e) {
            System.err.println("Failed to send notification: " + e.getMessage());
            return ResponseEntity.status(500).body("Notification failed: " + e.getMessage());
        }
    }
}
