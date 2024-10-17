package Dao.services.serviceImpl;

import entity.Customers;
import exception.CustomerNotFoundException;
import exception.DBConnectionException;
import Dao.ICustomerService;
import util.DBConnect;

import java.sql.*;

public class CustomerServiceImpl implements ICustomerService {
    private DBConnect dbConnect = new DBConnect();

    @Override
    public Customers getCustomerByID(int customerID) throws CustomerNotFoundException{
        Customers customers = null;
        String query = "SELECT * From Customer where CustomerID = ?";
        try(Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,customerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                customers = new Customers();
                customers.setCustomerID(resultSet.getInt("CustomerID"));
                customers.setFirstName(resultSet.getString("FirstName"));
                customers.setLastName(resultSet.getString("LastName"));
                customers.setEmail(resultSet.getString("Email"));
                customers.setPhoneNumber(resultSet.getString("PhoneNumber"));
                customers.setAddress(resultSet.getString("Address"));
                customers.setUsername(resultSet.getString("Username"));
                customers.setPassword(resultSet.getString("Password"));
                customers.setRegistrationDate(resultSet.getDate("RegistrationDate"));
            } else{
                throw new CustomerNotFoundException("Customer ID:" + customerID);
            }
        } catch(SQLException | DBConnectionException | CustomerNotFoundException e){
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customers getCustomerByUsername(String username) throws CustomerNotFoundException{
        Customers customers = null;
        String query = "SELECT * From Customer Where Username = ?";
        try(Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                customers = new Customers();
                customers.setCustomerID(resultSet.getInt("CustomerID"));
                customers.setFirstName(resultSet.getString("FirstName"));
                customers.setLastName(resultSet.getString("LastName"));
                customers.setEmail(resultSet.getString("Email"));
                customers.setPhoneNumber(resultSet.getString("PhoneNumber"));
                customers.setAddress(resultSet.getString("Address"));
                customers.setUsername(resultSet.getString("Username"));
                customers.setPassword(resultSet.getString("Password"));
                customers.setRegistrationDate(resultSet.getDate("RegistrationDate"));
            } else {
                throw new CustomerNotFoundException("Username "+ username);
            }
        }catch (SQLException|DBConnectionException|CustomerNotFoundException e){
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean registerCustomer(Customers customerData){
        String query = "INSERT into Customer (CustomerID, FirstName, LastName, Email, PhoneNumber, Address, Username, Password, registrationDate) Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, customerData.getCustomerID());
            preparedStatement.setString(2, customerData.getFirstName());
            preparedStatement.setString(3, customerData.getLastName());
            preparedStatement.setString(4, customerData.getEmail());
            preparedStatement.setString(5, customerData.getPhoneNumber());
            preparedStatement.setString(6, customerData.getAddress());
            preparedStatement.setString(7, customerData.getUsername());
            preparedStatement.setString(8, customerData.getPassword());
            preparedStatement.setDate(9, new java.sql.Date(customerData.getRegistrationDate().getTime()));
            int x = preparedStatement.executeUpdate();
            return x > 0;
        } catch (SQLException|DBConnectionException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customers customerData){
        String query = "Update Customer Set FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Address = ?, Username = ?, Password = ?, RegistrationDate = ? Where CustomerID = ?";
        try (Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, customerData.getFirstName());
            preparedStatement.setString(2, customerData.getLastName());
            preparedStatement.setString(3, customerData.getEmail());
            preparedStatement.setString(4, customerData.getPhoneNumber());
            preparedStatement.setString(5, customerData.getAddress());
            preparedStatement.setString(6, customerData.getUsername());
            preparedStatement.setString(7, customerData.getPassword());
            preparedStatement.setDate(8, (Date) (customerData.getRegistrationDate()));
            preparedStatement.setInt(9, customerData.getCustomerID());
            int rowsAffected =preparedStatement.executeUpdate();
            return rowsAffected>0;
        } catch (SQLException|DBConnectionException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int customerID) {
        String query = "Delete From Customer Where CustomerID = ?";

        try(Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, customerID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | DBConnectionException e) {
            e.printStackTrace();
        }
        return false;
    }

}
