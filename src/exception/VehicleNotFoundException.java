package exception;

public class VehicleNotFoundException extends Exception{
    public VehicleNotFoundException(String message){
        super("Vehicle not found: " + message);
    }
}
