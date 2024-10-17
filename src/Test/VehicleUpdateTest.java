package Test;

import Dao.services.VehicleService;
import Dao.services.serviceImpl.VehicleServiceImpl;
import entity.Vehicle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class VehicleUpdateTest {
    VehicleService vehicleService = new VehicleService();
    VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
    Vehicle vehicle = new Vehicle();

    @Before
    public void setUp(){
        VehicleService vehicleService = new VehicleService();
        VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
        Vehicle vehicle = new Vehicle();
    }

    @Test
    public void testUpdateVehicle() throws SQLException{
        vehicle.setVehicleID(10);
        vehicle.setModel("Elantra");
        vehicle.setMake("Hyundai");
        vehicle.setYear(2015);
        vehicle.setColor("Black");
        vehicle.setRegistrationNumber("AMC578");
        vehicle.setAvailability(true);
        vehicle.setDailyRate(9800.00);

        assertTrue(vehicleServiceImpl.updateVehicle(vehicle));
    }

    @After
    public void tearDown(){
        vehicleServiceImpl = null;
    }
}
