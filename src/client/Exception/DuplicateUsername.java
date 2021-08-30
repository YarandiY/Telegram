package client.Exception;

public class DuplicateUsername extends Exception {
    @Override
    public String getMessage() {
        return "change the username please";
    }
}
