package exception;

public class AdminNotFoundException extends Exception {
    public AdminNotFoundException(String message) {
        super("Admin not found: " + message);
    }
}