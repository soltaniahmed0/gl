package strategy;

import com.google.firebase.messaging.*;

public class FirebaseNotificationStrategy implements NotificationStrategy {
    private final FirebaseMessaging firebaseMessaging;

    public FirebaseNotificationStrategy(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    @Override
    public void send(String title, String body, String token) {
        try {
            Message message = Message.builder()
                    .setNotification(Notification.builder().setTitle(title).setBody(body).build())
                    .setToken(token)
                    .build();
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            System.err.println("Failed to send notification: " + e.getMessage());
        }
    }
}
