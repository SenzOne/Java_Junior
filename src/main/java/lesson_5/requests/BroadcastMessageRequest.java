package lesson_5.requests;

public class BroadcastMessageRequest extends Request{
    public static final String TYPE = "broadcastMessage";

    private String sender;
    private String message;

    public BroadcastMessageRequest() {
        setType(TYPE);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
