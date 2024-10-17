package main;

import Dao.services.AdminService;
import Dao.services.CustomerService;
import Dao.services.ReservationService;
import Dao.services.VehicleService;
import Dao.services.serviceImpl.AdminServiceImpl;
import Dao.services.serviceImpl.CustomerServiceImpl;
import Dao.services.serviceImpl.ReservationServiceImpl;
import Dao.services.serviceImpl.VehicleServiceImpl;
import entity.Admin;
import entity.Customers;
import entity.Reservation;
import entity.Vehicle;
import exception.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MainMethod {
    private static AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
    private static CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
    private static ReservationServiceImpl reservationServiceImpl = new ReservationServiceImpl();
    private static VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();



    public static void main(String[] args) throws SQLException, DBConnectionException, AdminNotFoundException, AuthenticationException, CustomerNotFoundException, ReservationException, VehicleNotFoundException {
        CustomerService customerService = new CustomerService();
        VehicleService vehicleService = new VehicleService();
        ReservationService reservationService = new ReservationService(reservationServiceImpl);
        AdminService adminService = new AdminService(adminServiceImpl);
        Customers customers = new Customers();
        Admin admin = new Admin();
        Scanner sc = new Scanner(System.in);
        String ch = "";
        int choice;

        do {
            System.out.println("***************Main Menu**************");
            System.out.println("1. Customer");
            System.out.println("2. Vehicle");
            System.out.println("3. Reservation");
            System.out.println("4. Admin");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    do {
                        System.out.println("***************CUSTOMER MENU***********************");
                        System.out.println("1.customer details through username");
                        System.out.println("2.register customer ");
                        System.out.println("3.update customer ");
                        System.out.println("4.delete customer");
                        System.out.println("5.customer details through id");
                        System.out.println("6.Exit");
                        System.out.println("Enter your choice: ");
                        int choice1 = sc.nextInt();
                        switch (choice1) {
                            case 1:
                                String username = getCustomerUsernameFromUserInput();
                                Customers user = customerServiceImpl.getCustomerByUsername(username);
                                System.out.println("Customer Information by username is: ");
                                System.out.println(user);
                                break;
                            case 2:
                                Scanner scanner = new Scanner(System.in);

                                System.out.println("Enter CustomerID: ");
                                int customerID = scanner.nextInt();
                                customers.setCustomerID(customerID);

                                System.out.println("Enter first name: ");
                                String fName = scanner.nextLine();
                                customers.setFirstName(scanner.next());

                                System.out.println("Enter last name: ");
                                String lName = scanner.nextLine();
                                customers.setLastName(sc.next());

                                System.out.println("Enter Email: ");
                                String email = scanner.nextLine();
                                customers.setEmail(email);

                                System.out.println("Enter Phone Number: ");
                                String phoneNumber = scanner.nextLine();
                                customers.setPhoneNumber(phoneNumber);
                                System.out.println("Enter Address: ");
                                String address = scanner.nextLine();
                                customers.setAddress(address);

                                System.out.println("Enter Username: ");
                                String username1 = scanner.nextLine();
                                customers.setUsername(username1);

                                System.out.println("Enter Password: ");
                                String password = scanner.nextLine();
                                customers.setPassword(password);

                                System.out.print("Enter registration Date: ");
                                String Date1 = scanner.next();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                LocalDate date = LocalDate.parse(Date1, formatter);
                                java.sql.Date registrationDate = java.sql.Date.valueOf(date);
                                customers.setRegistrationDate(registrationDate);

                                customerServiceImpl.registerCustomer(customers);
                                System.out.println("Customer registered successfully");
                                break;

                            case 3:
                                Scanner scanner1 = new Scanner(System.in);
                                System.out.print("Enter CustomerID to update: ");
                                int customerID1 = scanner1.nextInt();

                                System.out.print("Enter First name: ");
                                String fName1 = scanner1.next();
                                customers.setFirstName(fName1);

                                System.out.print("Enter Last Name: ");
                                String lName1 = scanner1.next();
                                customers.setLastName(lName1);

                                System.out.print("Enter Email: ");
                                String email1 = scanner1.next();
                                customers.setEmail(email1);

                                System.out.print("Enter Phone Number: ");
                                String phoneNumber1 = scanner1.next();
                                customers.setPhoneNumber(phoneNumber1);

                                System.out.print("Enter Address: ");
                                String address1 = scanner1.next();
                                customers.setAddress(address1);

                                System.out.print("Enter Username: ");
                                String username11 = scanner1.next();
                                customers.setUsername(username11);

                                System.out.print("Enter Password: ");
                                String password1 = scanner1.next();
                                customers.setPassword(password1);

                                System.out.print("Enter Date: ");
                                String date1 = scanner1.next();
                                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
                                Date registrationDate1 = Date.valueOf(date1);
                                customers.setRegistrationDate(registrationDate1);

                                Customers customers1 = new Customers(customerID1, fName1, lName1, email1, phoneNumber1, address1, username11, password1, registrationDate1);
                                customerServiceImpl.updateCustomer(customers1);
                                System.out.println("Customer updated successfully");
                                break;
                            case 4:
                                Scanner scanner2 = new Scanner(System.in);
                                System.out.println("Enter CustomerID: ");
                                int customerID2 = scanner2.nextInt();
                                customerServiceImpl.deleteCustomer(customerID2);
                                System.out.println("Deleted Successfully");
                                break;
                            case 5:
                                Scanner scanner3 = new Scanner(System.in);
                                System.out.print("Enter CustomerID: ");
                                int customerID3 = scanner3.nextInt();
                                Customers customerByID = customerServiceImpl.getCustomerByID(customerID3);
                                System.out.println("Customer Information by ID: ");
                                System.out.println(customerByID);
                                break;
                            case 6:
                                System.out.println("Exiting Customer Menu");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                        System.out.println("Do you want to continue? (Y/N)");
                        ch = sc.next();
                    } while (ch.equalsIgnoreCase("y"));
                    break;
                case 2:
                    do {
                        System.out.println("******************* VEHICLE MENU******************");
                        System.out.println("1. Add Vehicle");
                        System.out.println("2. Update Vehicle");
                        System.out.println("3. Delete Vehicle");
                        System.out.println("4. View Vehicle Details");
                        System.out.println("5. Get Available Vehicles");
                        System.out.println("6. Exit");
                        System.out.print("Enter your choice: ");
                        int choice2 = sc.nextInt();

                        switch (choice2) {
                            case 1:
                                System.out.print("Enter Vehicle ID: ");
                                int vehicleID = sc.nextInt();
                                System.out.print("Enter Vehicle model: ");
                                String model = sc.next();
                                System.out.print("Enter vehicle make: ");
                                String make = sc.next();
                                System.out.print("Enter manufacturing year: ");
                                int year = sc.nextInt();
                                System.out.print("Enter the vehicle color: ");
                                String color = sc.next();
                                System.out.print("Enter vehicle RegNo.: ");
                                String registrationNumber = sc.next();
                                System.out.print("Enter Vehicle avail: ");
                                boolean availability = sc.nextBoolean();
                                System.out.print("Enter Vehicle Price: ");
                                double dailyRate = sc.nextDouble();
                                Vehicle newVehicle1 = new Vehicle(vehicleID, model, make, year, color, registrationNumber, availability, dailyRate);
                                vehicleServiceImpl.addVehicle(newVehicle1);
                                System.out.println("Vehicle Added Successfully");
                                break;
                            case 2:
                                System.out.print("Enter Vehicle ID to update: ");
                                int updateVehicleID = sc.nextInt();

                                System.out.print("Enter Vehicle Model: ");
                                String model1 = sc.next();

                                System.out.print("Enter Vehicle make: ");
                                String make1 = sc.next();

                                System.out.print("Enter Vehicle manufacturing year: ");
                                int year1 = sc.nextInt();

                                System.out.print("Enter Vehicle Color: ");
                                String color1 = sc.next();

                                System.out.print("Enter Vehicle regNo: ");
                                String regNo1 = sc.next();
                                sc.nextLine();

                                System.out.print("Enter Vehicle availability: ");
                                boolean availability1 = sc.nextBoolean();

                                System.out.print("Enter Vehicle dailyRate: ");
                                double dailyRate1 = sc.nextDouble();

                                Vehicle newVehicle = new Vehicle(updateVehicleID, model1, make1, year1, color1, regNo1, availability1, dailyRate1);
                                vehicleServiceImpl.updateVehicle(newVehicle);
                                System.out.println("vehicle updated successfully.");
                                break;
                            case 3:
                                Scanner scanner2 = new Scanner(System.in);
                                System.out.print("Enter Vehicle ID: ");
                                int vehicleID2 = scanner2.nextInt();
                                vehicleServiceImpl.removeVehicle(vehicleID2);
                                System.out.println("deleted successfully");
                                break;
                            case 4:
                                System.out.print("Enter the Vehicle ID to view details: ");
                                Scanner scanner = new Scanner(System.in);
                                int vehicleID3 = scanner.nextInt();
                                try {
                                    Vehicle vehicle = vehicleServiceImpl.getVehicleByID(vehicleID3);

                                    if (vehicle != null) {
                                        System.out.println("Vehicle Details:");
                                        System.out.println("ID: " + vehicle.getVehicleID());
                                        System.out.println("Model: " + vehicle.getModel());
                                        System.out.println("Make: " + vehicle.getMake());
                                        System.out.println("Manufacture Year: " + vehicle.getYear());
                                        System.out.println("Color: " + vehicle.getColor());
                                        System.out.println("Registration Number: " + vehicle.getRegistrationNumber());
                                        System.out.println("Daily Rate: " + vehicle.getDailyRate());
                                    } else {
                                        System.out.println("Vehicle with ID " + vehicleID3 + " not found.");
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 5:
                                VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
                                List<Vehicle> availableVehicles = vehicleServiceImpl.getAvailableVehicles();
                                if(availableVehicles.isEmpty()){
                                    System.out.println("No vehicles available right now");
                                } else{
                                    System.out.println("List of all available vehicles");
                                    for(Vehicle vehicle : availableVehicles){
                                        System.out.println(vehicle);
                                    }
                                }
                                break;
                            case 6:
                                System.out.println("Exiting vehicle menu");
                                break;
                            default:
                                System.out.println("Invalid Choice. Please try again.");
                        }
                        System.out.println("Do you want to continue in Vehicle Menu? (Y/N)");
                        ch = sc.next();
                    } while (ch.equalsIgnoreCase("Y"));
                    break;
                case 3:
                    do {
                        System.out.println("**************** RESERVATION MENU*************************");
                        System.out.println("1. Make a reservation");
                        System.out.println("2. View reservations by ID");
                        System.out.println("3. View reservations by customer ID");
                        System.out.println("4. Cancel a reservation");
                        System.out.println("5. Update a reservation");
                        System.out.println("6. Exit");
                        System.out.print("Enter your choice: ");
                        int choice3 = sc.nextInt();

                        switch (choice3) {
                            case 1:
                                Reservation reservation = new Reservation();
                                Scanner scanner = new Scanner(System.in);

                                System.out.print("Enter Reservation ID: ");
                                int reservationID = scanner.nextInt();
                                reservation.setReservationID(reservationID);

                                System.out.print("Enter Customer ID: ");
                                int customerID = scanner.nextInt();
                                reservation.setCustomerID(customerID);

                                System.out.print("Enter Vehicle ID: ");
                                int vehicleID = scanner.nextInt();
                                reservation.setVehicleID(vehicleID);

                                System.out.print("Enter Start Date: ");
                                String date1 = scanner.next();
                                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
                                Date startDate = Date.valueOf(date1);
                                reservation.setStartDate(startDate);

                                System.out.print("Enter End Date: ");
                                String date11 = scanner.next();
                                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
                                Date endDate = Date.valueOf(date11);
                                reservation.setEndDate(endDate);

                                System.out.print("Enter total cost: ");
                                double totalCost = scanner.nextDouble();
                                reservation.setTotalCost(totalCost);

                                System.out.print("Enter status: ");
                                String status = scanner.next();
                                reservation.setStatus(status);

                                reservationServiceImpl.createReservation(reservation);
                                System.out.println("Reservation created successfully.");
                                break;
                            case 2:
                                System.out.println("Enter ID: ");
                                int ID = sc.nextInt();
                                Reservation reservationByID = reservationServiceImpl.getReservationByID(ID);
                                System.out.println("Reservation Information by ID: ");
                                System.out.println(reservationByID);
                                break;
                            case 3:
                                System.out.print("Enter customer ID: ");
                                int customerID1 = sc.nextInt();
                                List<Reservation> reservations = reservationServiceImpl.getReservationByCustomerID(customerID1);
                                System.out.println("Reservations for Customer ID " + customerID1 + ":");
                                for (Reservation res : reservations) {
                                    System.out.println(res);
                                }
                                break;
                            case 4:
                                System.out.print("Enter Reservation ID to cancel: ");
                                int reservationID1 = sc.nextInt();
                                reservationServiceImpl.cancelReservation(reservationID1);
                                System.out.println("Reservation with ID " + reservationID1 + " canceled successfully.");
                                break;
                            case 5:
                                Reservation reservation1 = new Reservation();
                                Scanner scanner3 = new Scanner(System.in);

                                System.out.print("Enter Reservation ID to update: ");
                                int reservationID2 = scanner3.nextInt();
                                reservation1.setReservationID(reservationID2);

                                System.out.print("Enter Customer ID: ");
                                int customerID2 = scanner3.nextInt();
                                reservation1.setCustomerID(customerID2);

                                System.out.print("Enter Vehicle ID: ");
                                int vehicleID2 = scanner3.nextInt();
                                reservation1.setVehicleID(vehicleID2);

                                System.out.print("Enter Start Date: ");
                                String date111 = scanner3.next();
                                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
                                Date startDate1 = Date.valueOf(date111);
                                reservation1.setStartDate(startDate1);

                                System.out.print("Enter End Date: ");
                                String date112 = scanner3.next();
                                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
                                Date endDate1 = Date.valueOf(date112);
                                reservation1.setEndDate(endDate1);

                                System.out.print("Enter total cost: ");
                                double totalCost1 = scanner3.nextDouble();
                                reservation1.setTotalCost(totalCost1);

                                System.out.print("Enter status: ");
                                String status1 = scanner3.next();
                                reservation1.setStatus(status1);

                                reservationServiceImpl.updateReservation(reservation1);
                                System.out.println("Reservation updated successfully.");
                                break;
                            case 6:
                                System.out.println("Exiting the reservation menu.");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                        System.out.println("Do you want to continue with reservations? (Y/N)");
                        ch = sc.next();
                    } while (ch.equalsIgnoreCase("Y"));
                    break;
                case 4:
                    do {
                        Scanner scanner = new Scanner(System.in);

                        System.out.println("*************** ADMIN MENU****************");
                        System.out.println("1. Get Admin by ID");
                        System.out.println("2. Get Admin by Username");
                        System.out.println("3. Register Admin");
                        System.out.println("4. Update Admin");
                        System.out.println("5. Delete Admin");
                        System.out.println("6. Exit");
                        System.out.print("Enter your choice: ");
                        int choice1 = scanner.nextInt();

                        switch (choice1) {
                            case 1:
                                System.out.print("Enter AdminID: ");
                                int adminID = scanner.nextInt();
                                try {
                                    Admin adminByID = adminServiceImpl.getAdminByID(adminID);
                                    System.out.println(adminByID);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                System.out.print("Enter Admin Username: ");
                                String username = scanner.next();
                                try {
                                    Admin adminByUsername = adminServiceImpl.getAdminByUsername(username);
                                    System.out.println(adminByUsername);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                Admin admin1 = new Admin();
                                Scanner scanner1 = new Scanner(System.in);
                                System.out.println("Enter Admin ID: ");
                                int adminID1 = scanner1.nextInt();
                                System.out.print("Enter First Name: ");
                                String fName = scanner1.next();
                                admin1.setFirstName(fName);

                                System.out.print("Enter Last Name: ");
                                String lName = scanner1.next();
                                admin1.setLastName(lName);

                                System.out.print("Enter Email: ");
                                String email = scanner1.next();
                                admin1.setEmail(email);

                                System.out.print("Enter Phone Number: ");
                                String phoneNum = scanner1.next();
                                admin1.setPhoneNumber(phoneNum);

                                System.out.print("Enter Username: ");
                                String username1 = scanner1.next();
                                admin1.setUsername(username1);

                                System.out.print("Enter Password: ");
                                String password = scanner1.next();
                                admin1.setPassword(password);

                                System.out.print("Enter Roles: ");
                                String roles = scanner1.next();
                                admin1.setRole(roles);

                                System.out.print("Enter Join Date: ");
                                String Date1 = scanner.next();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                LocalDate date = LocalDate.parse(Date1, formatter);
                                java.sql.Date joinDate = java.sql.Date.valueOf(date);
                                admin1.setJoinDate(joinDate);

                                adminServiceImpl.registerAdmin(admin1);
                                System.out.println("successfully registered");
                                break;
                            case 4:

                                Scanner scanner2 = new Scanner(System.in);
                                System.out.print("Enter Admin ID to update: ");
                                int adminID2 = scanner2.nextInt();
                                scanner2.nextLine();

                                System.out.print("Enter First Name: ");
                                String fName1 = scanner2.nextLine();
                                admin.setFirstName(fName1);

                                System.out.print("Enter Last Name: ");
                                String lName1 = scanner2.nextLine();
                                admin.setLastName(lName1);

                                System.out.print("Enter Email: ");
                                String email1 = scanner2.nextLine();
                                admin.setEmail(email1);

                                System.out.print("Enter Phone Number: ");
                                String phoneNum1 = scanner2.nextLine();
                                admin.setPhoneNumber(phoneNum1);

                                System.out.print("Enter Username: ");
                                String username11 = scanner2.nextLine();
                                admin.setUsername(username11);

                                System.out.print("Enter Password: ");
                                String password1 = scanner2.nextLine();
                                admin.setPassword(password1);

                                System.out.print("Enter Roles: ");
                                String roles1 = scanner2.nextLine();
                                admin.setRole(roles1);

                                System.out.print("Enter Join Date: ");
                                String Date2 = scanner.next();
                                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                LocalDate date1 = LocalDate.parse(Date2, formatter1);
                                java.sql.Date joinDate1 = java.sql.Date.valueOf(date1);
                                admin.setJoinDate(joinDate1);
                                Admin admin2 = new Admin(adminID2,fName1,lName1,email1,phoneNum1,username11,password1,roles1,joinDate1);
                                adminServiceImpl.updateAdmin(admin2);
                                System.out.println("Admin Updated Successfully");
                                break;
                            case 5:
                                Scanner sc1 = new Scanner(System.in);
                                System.out.print("Enter AdminID to delete: ");
                                int adminIDToDelete = sc1.nextInt();
                                try {
                                    adminServiceImpl.deleteAdmin(adminIDToDelete);
                                    System.out.println("Admin with ID " + adminIDToDelete + " deleted successfully.");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 6:
                                System.out.println("Exiting Admin Menu");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                        System.out.println("Do you want to continue in Admin Menu? (Y/N)");
                        ch = sc.next();
                    } while (ch.equalsIgnoreCase("Y"));
                    break;
                default:
                    System.out.println("Invalid Choice. Please Try Again.");
                    break;
            }
            System.out.println("Do you want to continue in Main Menu? (Y/N)");
            ch = sc.next();
        }while(ch.equalsIgnoreCase("y"));
        System.out.println("Thank you for visiting. See you soon");
    }
    private static String getCustomerUsernameFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer username: ");
        return scanner.nextLine();
    }
}
