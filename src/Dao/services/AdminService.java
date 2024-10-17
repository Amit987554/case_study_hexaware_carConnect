package Dao.services;

import entity.Admin;
import exception.AdminNotFoundException;
import exception.DBConnectionException;
import Dao.IAdminService;
import Dao.services.serviceImpl.AdminServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements IAdminService {
    private List<Admin> adminList;

    public AdminService(AdminServiceImpl adminServiceImpl){
        this.adminList = new ArrayList<>();
    }

    public Admin getAdminByID(int adminID) throws DBConnectionException, SQLException, AdminNotFoundException {
        for (Admin admin : adminList){
            if (admin.getAdminID() == adminID){
                return admin;
            }
        }
        return null;
    }

    public Admin getAdminByUsername(String username) throws DBConnectionException, SQLException, AdminNotFoundException{
        for (Admin admin: adminList){
            if (admin.getUsername().equals(username)){
                return admin;
            }
        }
        return null;
    }

    public boolean registerAdmin(Admin adminData) throws DBConnectionException, SQLException, AdminNotFoundException{
        if(getAdminByUsername(adminData.getUsername()) != null){
            return false;
        }
        adminList.add(adminData);
        return true;
    }

    public boolean updateAdmin(Admin adminData) throws DBConnectionException, SQLException{
        for (int i = 0; i<adminList.size();i++){
            Admin admin = adminList.get(i);
            if(admin.getAdminID() == adminData.getAdminID()){
                adminList.set(i,adminData);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAdmin(int adminID) throws DBConnectionException, SQLException{
        for (Admin admin : adminList){
            if(admin.getAdminID() == adminID){
                adminList.remove(admin);
                return true;
            }
        }
        return false;
    }
}
