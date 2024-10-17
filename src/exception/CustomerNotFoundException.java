package exception;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super("Customer not found: " + message);
    }
}
