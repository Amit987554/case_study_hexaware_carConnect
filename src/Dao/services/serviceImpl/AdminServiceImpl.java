package Dao.services.serviceImpl;

import entity.Admin;
import exception.AdminNotFoundException;
import exception.DBConnectionException;
import Dao.IAdminService;
import util.DBConnect;

import java.sql.*;

public class AdminServiceImpl implements IAdminService {
    private DBConnect dbConnect = new DBConnect();

    @Override
    public Admin getAdminByID(int adminID) throws SQLException,AdminNotFoundException{
        Admin admin = null;
        String query = "Select * From admin where AdminID = ?";
        try(Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1,adminID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                admin = new Admin();
                admin.setAdminID(resultSet.getInt("AdminID"));
                admin.setFirstName(resultSet.getString("FirstName"));
                admin.setLastName(resultSet.getString("LastName"));
                admin.setEmail(resultSet.getString("Email"));
                admin.setPhoneNumber(resultSet.getString("PhoneNumber"));
                admin.setUsername(resultSet.getString("Username"));
                admin.setPassword(resultSet.getString("Password"));
                admin.setRole(resultSet.getString("Role"));
                admin.setJoinDate(resultSet.getDate("JoinDate"));
            }
            else{
                throw new AdminNotFoundException("Admin with ID" +adminID + "not found.");
            }
        } catch (SQLException|AdminNotFoundException| DBConnectionException e){
            e.printStackTrace();
        } return admin;
    }

    @Override
    public Admin getAdminByUsername(String username) throws SQLException, AdminNotFoundException{
        Admin admin = null;
        String query = "Select * from Admin Where Username = ?";
        try (Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                admin = new Admin();
                admin = new Admin();
                admin.setAdminID(resultSet.getInt("AdminID"));
                admin.setFirstName(resultSet.getString("FirstName"));
                admin.setLastName(resultSet.getString("LastName"));
                admin.setEmail(resultSet.getString("Email"));
                admin.setPhoneNumber(resultSet.getString("PhoneNumber"));
                admin.setUsername(resultSet.getString("Username"));
                admin.setPassword(resultSet.getString("Password"));
                admin.setRole(resultSet.getString("Role"));
                admin.setJoinDate(resultSet.getDate("JoinDate"));
            }
            else{
                throw new AdminNotFoundException("Admin with username " + username + " not found");
            }
        } catch(SQLException|AdminNotFoundException|DBConnectionException e){
            e.printStackTrace();
        } return admin;
    }

    @Override
    public boolean registerAdmin(Admin adminData) throws SQLException{
        String query = "Insert into Admin(AdminID, FirstName,LastName,Email,PhoneNumber,Username,Password,Role,JoinDate)" + "Values(?,?,?,?,?,?,?,?,?)";
        try(Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, adminData.getAdminID());
            preparedStatement.setString(2,adminData.getFirstName());
            preparedStatement.setString(3, adminData.getLastName());
            preparedStatement.setString(4, adminData.getEmail());
            preparedStatement.setString(5, adminData.getPhoneNumber());
            preparedStatement.setString(6, adminData.getUsername());
            preparedStatement.setString(7, adminData.getPassword());
            preparedStatement.setString(8, adminData.getRole());
            preparedStatement.setDate(9, (Date) adminData.getJoinDate());
            return preparedStatement.executeUpdate()>0;
        } catch(SQLException|DBConnectionException e){
            e.printStackTrace();
        } return false;
    }

    @Override
    public boolean updateAdmin(Admin adminData) throws SQLException{
        String query = "UPDATE Admin SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Username = ?, Password = ?, Role = ?, JoinDate = ? WHERE AdminID = ?";
        try(Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, adminData.getFirstName());
            preparedStatement.setString(2, adminData.getLastName());
            preparedStatement.setString(3, adminData.getEmail());
            preparedStatement.setString(4, adminData.getPhoneNumber());
            preparedStatement.setString(5, adminData.getUsername());
            preparedStatement.setString(6, adminData.getPassword());
            preparedStatement.setString(7, adminData.getRole());
            preparedStatement.setDate(8, (Date) adminData.getJoinDate());
            preparedStatement.setInt(9, adminData.getAdminID());
            return preparedStatement.executeUpdate()>0;
        }catch(SQLException|DBConnectionException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAdmin(int adminID) throws SQLException{
        String query = "Delete from Admin where AdminID = ?";
        try(Connection connection = dbConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,adminID);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException|DBConnectionException e){
            e.printStackTrace();
        }
        return false;
    }
}
