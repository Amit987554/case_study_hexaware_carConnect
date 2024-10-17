package exception;

public class ReservationException extends Exception{
    public ReservationException(String message){
        super("Reservation error: " +message);
    }
}
