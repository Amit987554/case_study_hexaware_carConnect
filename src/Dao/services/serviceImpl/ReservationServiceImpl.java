package Dao.services.serviceImpl;

import entity.Reservation;
import exception.DBConnectionException;
import exception.ReservationException;
import Dao.IReservationService;
import util.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class ReservationServiceImpl implements IReservationService {
    private DBConnect dbConnect = new DBConnect();

    @Override
    public Reservation getReservationByID(int reservationID) throws SQLException, ReservationException {
        Reservation reservation = null;
        Connection connection = null;
        try{
            connection = dbConnect.getConnection();
            String query = "Select * From Reservation Where ReservationID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservationID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                reservation = new Reservation(
                        resultSet.getInt("ReservationID"),
                        resultSet.getInt("CustomerID"),
                        resultSet.getInt("VehicleID"),
                        resultSet.getDate("StartDate"),
                        resultSet.getDate("EndDate"),
                        resultSet.getDouble("TotalCost"),
                        resultSet.getString("Status")
                );
            }else{
                throw new ReservationException("Reservation with ID " + reservationID + " not found.");
            }
        } catch (SQLException | DBConnectionException e) {
            e.printStackTrace();
        }finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("error connecting to db in getReservationByID() method in ReservationService class");
                    e.printStackTrace();
                }
            }
        }
        return reservation;
    }

    @Override
    public List<Reservation> getReservationByCustomerID(int customerID) throws SQLException, ReservationException{
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dbConnect.getConnection();
            String query = "Select * From Reservation Where CustomerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, customerID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("ReservationID"),
                        resultSet.getInt("CustomerID"),
                        resultSet.getInt("VehicleID"),
                        resultSet.getDate("StartDate"),
                        resultSet.getDate("EndDate"),
                        resultSet.getDouble("TotalCost"),
                        resultSet.getString("Status")
                );
                reservations.add(reservation);
                return reservations;
            }
        } catch (SQLException | DBConnectionException e) {
            e.printStackTrace();
        }finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error in connection to db!! On getReservationByCustomerID() method on ReservationService class");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean createReservation(Reservation reservationData) throws SQLException {
        Connection connection = null;
        boolean isCreated = false;
        try {
            connection = dbConnect.getConnection();
            String query = "Insert Into Reservation (ReservationID, CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,reservationData.getReservationID());
            preparedStatement.setInt(2, reservationData.getCustomerID());
            preparedStatement.setInt(3, reservationData.getVehicleID());
            preparedStatement.setDate(4, (Date) reservationData.getStartDate());
            preparedStatement.setDate(5, (Date) reservationData.getEndDate());
            preparedStatement.setDouble(6, reservationData.getTotalCost());
            preparedStatement.setString(7, reservationData.getStatus());
            int rowsInserted = preparedStatement.executeUpdate();
            isCreated = rowsInserted > 0;
        } catch (SQLException | DBConnectionException e) {
            System.out.println("throws sql exception in createReservation() method ");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return isCreated;
    }

    @Override
    public boolean updateReservation(Reservation reservationData) throws SQLException{
        Connection connection = null;
        boolean isUpdated = false;
        try {
            connection = DBConnect.getConnection();
            String query = "Update Reservation SET CustomerID = ?, VehicleID = ?, StartDate = ?, EndDate = ?, TotalCost = ?, Status = ? WHERE ReservationID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservationData.getCustomerID());
            preparedStatement.setInt(2, reservationData.getVehicleID());
            preparedStatement.setDate(3, new java.sql.Date(reservationData.getStartDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(reservationData.getEndDate().getTime()));
            preparedStatement.setDouble(5, reservationData.getTotalCost());
            preparedStatement.setString(6, reservationData.getStatus());
            preparedStatement.setInt(7, reservationData.getReservationID());
            int rowsUpdated = preparedStatement.executeUpdate();
            isUpdated = rowsUpdated > 0;
        } catch (SQLException | DBConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return isUpdated;
    }

    @Override
    public boolean cancelReservation(int reservationID) throws SQLException{
        Connection connection = null;
        boolean isCanceled = false;
        try {
            connection = DBConnect.getConnection();
            String sql = "Delete From Reservation Where ReservationID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reservationID);
            int rowsDeleted = statement.executeUpdate();
            isCanceled = rowsDeleted > 0;
        } catch (SQLException | DBConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return isCanceled;
    }
}
