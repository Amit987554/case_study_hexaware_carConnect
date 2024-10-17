package Test;

import Dao.services.CustomerService;
import Dao.services.serviceImpl.CustomerServiceImpl;
import entity.Customers;
import exception.CustomerNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class UpdateCustomerTest {
    CustomerService cust = new CustomerService();
    Customers customers = new Customers();
    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

    @Before
    public void setUp(){
        cust = new CustomerService();
    }

    @Test
    public void testUpdateCustomer() throws CustomerNotFoundException {

        customers.setCustomerID(2);
        customers.setFirstName("Amit");
        customers.setLastName("Bhatt");
        customers.setEmail("AB@gmail.com");
        customers.setPhoneNumber("8595330521");
        customers.setAddress("Noida");
        customers.setUsername("Amit@123");
        customers.setPassword("Amit@123");
        Date registrationDate = new Date(2024-12-12);
        customers.setRegistrationDate(registrationDate);
        assertTrue(customerServiceImpl.updateCustomer(customers));

    }

    @After
    public void tearDown(){
        customerServiceImpl = null;
    }
}
