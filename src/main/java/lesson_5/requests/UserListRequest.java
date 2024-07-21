package lesson_5.requests;

public class UserListRequest extends Request{
    public static final String TYPE = "getUserList";

    public UserListRequest() {
        setType(TYPE);
    }
}
