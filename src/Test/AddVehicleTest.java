package Test;

import Dao.services.VehicleService;
import Dao.services.serviceImpl.VehicleServiceImpl;
import entity.Vehicle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class AddVehicleTest {
    VehicleService vehicleService;
    VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
    Vehicle vehicle = new Vehicle();

    @Before
    public void setUp(){
        Vehicle vehicle = new Vehicle();
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        VehicleService vehicleService = new VehicleService();
    }

    @Test
    public void testAddVehicle() throws SQLException{
        vehicle.setModel("Furai");
        vehicle.setYear(2020);
        vehicle.setColor("Brown");
        vehicle.setMake("Mazda");
        vehicle.setRegistrationNumber("9809");
        vehicle.setDailyRate(9800.00);
        assertTrue(vehicleServiceImpl.addVehicle(vehicle));
    }

    @After
    public void tearDown(){
        vehicleServiceImpl = null;
    }
}
