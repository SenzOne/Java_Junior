package lesson_5.requests;

public class DisconnectRequest extends Request {
    public static final String TYPE = "disconnect";

    public DisconnectRequest() {
        setType(TYPE);
    }
}
