package Dao.services.serviceImpl;

import entity.Vehicle;
import exception.DBConnectionException;
import exception.InvalidInputException;
import exception.VehicleNotFoundException;
import Dao.IVehicleService;
import util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleServiceImpl implements IVehicleService {
    private DBConnect dbConnect = new DBConnect();

    @Override
    public Vehicle getVehicleByID(int vehicleID) throws SQLException, VehicleNotFoundException{
        Vehicle vehicle = null;
        String query = "Select * From Vehicle Where VehicleID = ?";
        try(Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

                preparedStatement.setInt(1,vehicleID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    vehicle = new Vehicle();
                    vehicle.setVehicleID(resultSet.getInt("VehicleID"));
                    vehicle.setModel(resultSet.getString("Model"));
                    vehicle.setMake(resultSet.getString("Make"));
                    vehicle.setYear(resultSet.getInt("Year"));
                    vehicle.setColor(resultSet.getString("Color"));
                    vehicle.setRegistrationNumber(resultSet.getString("RegistrationNumber"));
                    vehicle.setAvailability(resultSet.getBoolean("Availability"));
                    vehicle.setDailyRate(resultSet.getDouble("DailyRate"));
                }
                else{
                    throw new VehicleNotFoundException("Vehicle with ID" + vehicleID + "not found");
                }
        } catch (SQLException|VehicleNotFoundException| DBConnectionException e){
            e.printStackTrace();
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getAvailableVehicles() throws SQLException{
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "Select * From Vehicle Where Availability = true";
        try(Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Vehicle vehicle = new Vehicle(
                        resultSet.getInt("VehicleID"),
                        resultSet.getString("Model"),
                        resultSet.getString("Make"),
                        resultSet.getInt("Year"),
                        resultSet.getString("Color"),
                        resultSet.getString("RegistrationNumber"),
                        resultSet.getBoolean("Availability"),
                        resultSet.getDouble("DailyRate")
                );
            vehicles.add(vehicle);
            }
        } catch (SQLException|DBConnectionException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public boolean addVehicle(Vehicle vehicleData) throws SQLException{
        String query = "INSERT INTO Vehicle (VehicleID, Model, Make, Year, Color, RegistrationNumber, Availability, DailyRate) Values (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            if (vehicleData.getModel() == null) {
                throw new InvalidInputException("Model cannot be null or empty.");
            }
            if (vehicleData.getMake() == null) {
                throw new InvalidInputException("Make cannot be null or empty.");
            }
            if (vehicleData.getYear() <= 0) {
                throw new InvalidInputException("Year must be a positive integer.");
            }
            if (vehicleData.getColor() == null) {
                throw new InvalidInputException("Color cannot be null or empty.");
            }
            if (vehicleData.getRegistrationNumber() == null) {
                throw new InvalidInputException("Registration number cannot be null or empty.");
            }
            if (vehicleData.getDailyRate() <= 0) {
                throw new InvalidInputException("Daily rate must be a positive value.");
            }

            preparedStatement.setInt(1, vehicleData.getVehicleID());
            preparedStatement.setString(2, vehicleData.getModel());
            preparedStatement.setString(3, vehicleData.getMake());
            preparedStatement.setInt(4, vehicleData.getYear());
            preparedStatement.setString(5, vehicleData.getColor());
            preparedStatement.setString(6, vehicleData.getRegistrationNumber());
            preparedStatement.setBoolean(7, vehicleData.getAvailability());
            preparedStatement.setDouble(8, vehicleData.getDailyRate());

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException|InvalidInputException|DBConnectionException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateVehicle(Vehicle vehicleData) throws SQLException {
        String sql = "Update Vehicle Set Model = ?, Make = ?, Year = ?, Color = ?, RegistrationNumber = ?, Availability = ?, DailyRate = ? Where VehicleID = ?";

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (vehicleData.getModel() == null) {
                throw new InvalidInputException("Model cannot be null or empty");
            }
            if (vehicleData.getMake() == null) {
                throw new InvalidInputException("Make cannot be null or empty");
            }
            if (vehicleData.getYear() <= 0) {
                throw new InvalidInputException("Year must be a positive integer");
            }
            if (vehicleData.getColor() == null) {
                throw new InvalidInputException("Color cannot be null or empty");
            }
            if (vehicleData.getRegistrationNumber() == null) {
                throw new InvalidInputException("Registration number cannot be null or empty");
            }
            if (vehicleData.getDailyRate() <= 0) {
                throw new InvalidInputException("Daily rate must be a positive value");
            }
            preparedStatement.setString(1, vehicleData.getModel());
            preparedStatement.setString(2, vehicleData.getMake());
            preparedStatement.setInt(3, vehicleData.getYear());
            preparedStatement.setString(4, vehicleData.getColor());
            preparedStatement.setString(5, vehicleData.getRegistrationNumber());
            preparedStatement.setBoolean(6, vehicleData.getAvailability());
            preparedStatement.setDouble(7, vehicleData.getDailyRate());
            preparedStatement.setInt(8, vehicleData.getVehicleID());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new VehicleNotFoundException("Vehicle with ID " + vehicleData.getVehicleID() + " not found.");
            }
            return true;
        } catch (SQLException | VehicleNotFoundException | InvalidInputException | DBConnectionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeVehicle(int vehicleID) throws SQLException{
        String sql = "Delete From Vehicle Where VehicleID = ?";

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, vehicleID);
                return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | DBConnectionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
