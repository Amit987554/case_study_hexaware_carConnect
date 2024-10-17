package Dao;

import entity.Reservation;
import exception.DBConnectionException;
import exception.ReservationException;

import java.sql.SQLException;
import java.util.List;

public interface IReservationService {
    Reservation getReservationByID(int reservationID) throws DBConnectionException, SQLException, ReservationException;
    List<Reservation> getReservationByCustomerID(int customerID) throws DBConnectionException, SQLException, ReservationException;
    boolean createReservation(Reservation reservationData) throws DBConnectionException, SQLException;
    boolean updateReservation(Reservation reservationData) throws DBConnectionException, SQLException;
    boolean cancelReservation(int reservationID) throws  DBConnectionException, SQLException;
}
