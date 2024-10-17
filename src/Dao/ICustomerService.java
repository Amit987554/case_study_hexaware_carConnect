package Dao;

import entity.Customers;
import exception.CustomerNotFoundException;
import exception.DBConnectionException;

public interface ICustomerService {
    Customers getCustomerByID(int customerID) throws DBConnectionException, CustomerNotFoundException;
    Customers getCustomerByUsername(String username) throws DBConnectionException, CustomerNotFoundException;
    boolean registerCustomer(Customers customerData) throws DBConnectionException, CustomerNotFoundException;
    boolean updateCustomer(Customers customerData) throws DBConnectionException;
    boolean deleteCustomer(int customerID) throws DBConnectionException;
}
