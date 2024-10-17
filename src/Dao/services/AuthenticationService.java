package Dao.services;

import Dao.services.serviceImpl.CustomerServiceImpl;
import entity.Admin;
import entity.Customers;
import exception.AuthenticationException;
import exception.CustomerNotFoundException;
import exception.DBConnectionException;

public class AuthenticationService {

    Customers customers;

    public boolean authenticate(String username, String password) throws AuthenticationException, DBConnectionException, CustomerNotFoundException {
        CustomerService cust = new CustomerService();
        customers = cust.getCustomerByUsername(username);
        if (customers != null && customers.getPassword() != null && customers.getPassword().equals(password)) {
            return false;
        } else {
            return true;
        }
    }
}
