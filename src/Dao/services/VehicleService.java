package Dao.services;

import Dao.IVehicleService;
import entity.Admin;
import entity.Vehicle;
import exception.DBConnectionException;
import exception.VehicleNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleService implements IVehicleService {

    private List<Vehicle> vehicles = new ArrayList<>();

    public Vehicle getVehicleByID(int vehicleID) throws DBConnectionException, SQLException, VehicleNotFoundException {
        for(Vehicle vehicle:vehicles){
            if (vehicle.getVehicleID() == vehicleID){
                return vehicle;
            }
        }
        return null;
    }

    public List<Vehicle> getAvailableVehicles() throws DBConnectionException, SQLException{
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle:vehicles){
            if(vehicle.isAvailability()){
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public boolean addVehicle(Vehicle vehicleData) throws DBConnectionException, VehicleNotFoundException, SQLException{
        try{
            vehicles.add(vehicleData);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateVehicle(Vehicle vehicleData) throws DBConnectionException, SQLException {
        for (int i = 0; i<vehicles.size();i++){
            Vehicle vehicle = vehicles.get(i);
            if(vehicle.getVehicleID() == vehicleData.getVehicleID()){
                vehicles.set(i,vehicleData);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeVehicle(int vehicleID) throws DBConnectionException, SQLException {
        for (Vehicle vehicle : vehicles){
            if(vehicle.getVehicleID() == vehicleID){
                vehicles.remove(vehicle);
                return true;
            }
        }
        return false;
    }
}
