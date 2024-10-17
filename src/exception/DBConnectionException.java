package exception;

public class DBConnectionException extends Exception{
    public DBConnectionException(String message){
        super("Database connection failed: " + message);
    }
}
