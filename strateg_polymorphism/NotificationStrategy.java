package strateg_polymorphism;

public interface NotificationStrategy {
    void send(String title, String body, String target);
}