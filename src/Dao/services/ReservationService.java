package Dao.services;

import Dao.IReservationService;
import Dao.services.serviceImpl.ReservationServiceImpl;
import entity.Reservation;
import exception.DBConnectionException;
import exception.ReservationException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationService implements IReservationService {

    private List<Reservation> reservationList;

    public ReservationService(ReservationServiceImpl reservationServiceImpl){
        this.reservationList = new ArrayList<>();
    }

    public Reservation getReservationByID(int reservationID) throws DBConnectionException, SQLException, ReservationException {
        for (Reservation reservation : reservationList){
            if(reservation.getReservationID() == reservationID){
                return reservation;
            }
        }
        return null;
    }

    public List<Reservation> getReservationByCustomerID(int customerID) throws DBConnectionException, SQLException, ReservationException{
        List<Reservation> customerReservation = new ArrayList<>();
        for(Reservation reservation: reservationList){
            if(reservation.getCustomerID() == customerID){
                customerReservation.add(reservation);
            }
        }
        return customerReservation;
    }

    public boolean updateReservation(Reservation reservationData) throws DBConnectionException, SQLException{
        for(int i = 0; i < reservationList.size(); i++) {
            Reservation reservation = reservationList.get(i);
            if(reservation.getReservationID() == reservationData.getReservationID()){
                reservationList.set(i,reservationData);
                return true;
            }
        }
        return false;
    }

    public boolean createReservation(Reservation reservationData) throws DBConnectionException, SQLException{
        reservationList.add(reservationData);
        return true;
    }

    public boolean cancelReservation(int reservationID) throws DBConnectionException, SQLException{
        for(Reservation reservation:reservationList){
            if (reservation.getReservationID() == reservationID){
                reservationList.remove(reservation);
                return true;
            }
        }
        return false;
    }
}
