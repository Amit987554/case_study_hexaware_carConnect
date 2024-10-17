package Dao;

import entity.Admin;
import exception.AdminNotFoundException;
import exception.DBConnectionException;

import java.sql.SQLException;

public interface IAdminService {
    Admin getAdminByID(int adminID) throws DBConnectionException, SQLException, AdminNotFoundException;
    Admin getAdminByUsername(String username) throws DBConnectionException, SQLException, AdminNotFoundException;
    boolean registerAdmin(Admin adminData) throws DBConnectionException, SQLException, AdminNotFoundException;
    boolean updateAdmin(Admin adminData) throws DBConnectionException, SQLException;
    boolean deleteAdmin(int adminID) throws DBConnectionException, SQLException;
}