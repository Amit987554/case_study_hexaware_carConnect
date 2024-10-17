package Test;

import Dao.services.AuthenticationService;
import Dao.services.serviceImpl.CustomerServiceImpl;
import entity.Customers;
import exception.AuthenticationException;
import exception.CustomerNotFoundException;
import exception.DBConnectionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CustomerAuthenticationTest {
    AuthenticationService authenticationService;
    CustomerServiceImpl customerServiceImpl;

    @Before
    public void setUp(){
        authenticationService = new AuthenticationService();
    }

    @Test
    public void testCustomerAuthentication() throws AuthenticationException, DBConnectionException, CustomerNotFoundException {
        String username = "VIBHAs";
        String password = "Vibhor@123";

        boolean isAuthenticated = authenticationService.authenticate(username,password);

        assertTrue("Authentication failed for valid credentials. Username: " + username + ", password: " + password,isAuthenticated);
    }

    @After
    public void tearDown(){
        authenticationService = null;
    }
}
