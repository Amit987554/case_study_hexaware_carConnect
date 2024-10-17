package Dao.services;

import Dao.ICustomerService;
import entity.Customers;
import exception.CustomerNotFoundException;
import exception.DBConnectionException;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {
    private List<Customers> customers = new ArrayList<>();

    public Customers getCustomerByID(int customerID)  throws DBConnectionException, CustomerNotFoundException {
        for (Customers customer : customers){
            if(customer.getCustomerID() == customerID){
                return customer;
            }
        }
        return null;
    }

    public Customers getCustomerByUsername(String username)  throws DBConnectionException, CustomerNotFoundException{
        for (Customers customer : customers){
            if(customer.getUsername().equals(username)){
                return customer;
            }
        }
        return null;
    }

    public boolean registerCustomer(Customers customerData)  throws DBConnectionException, CustomerNotFoundException{
        if(getCustomerByUsername(customerData.getUsername()) != null){
            return false;
        }
        customers.add(customerData);
        return true;
    }

    public boolean updateCustomer(Customers customer) throws DBConnectionException{
        for (int i = 0;i<customers.size();i++){
            if(customers.get(i).getCustomerID()==customer.getCustomerID()){
                customers.set(i,customer);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCustomer(int customerID) throws DBConnectionException{
        for(Customers customer:customers){
            if(customer.getCustomerID() == customerID){
                customers.remove(customer);
                return true;
            }
        }
        return false;
    }
}
