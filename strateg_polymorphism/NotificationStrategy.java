package strategy;

public interface NotificationStrategy {
    void send(String title, String body, String target) throws Exception;
}
