package Dao;

import entity.Vehicle;
import exception.DBConnectionException;
import exception.VehicleNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface IVehicleService {
    Vehicle getVehicleByID(int vehicleID) throws DBConnectionException, SQLException, VehicleNotFoundException;
    List<Vehicle> getAvailableVehicles() throws DBConnectionException, SQLException;
    boolean addVehicle(Vehicle vehicleData) throws DBConnectionException, VehicleNotFoundException, SQLException;
    boolean updateVehicle(Vehicle vehicleData) throws DBConnectionException, SQLException;
    boolean removeVehicle(int vehicleID) throws DBConnectionException, SQLException;
}
