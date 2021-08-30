package client.Exception;


public class UserNotFind extends Exception {
    @Override
    public String getMessage() {
        return "the user name or pass word is wrong!";
    }
}
