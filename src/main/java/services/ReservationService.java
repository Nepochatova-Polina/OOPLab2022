package services;

import database.implementations.Reservation_impl;
import database.interfaces.ReservationDAO;
import entities.Apartment_Reserv.Reservation;

import java.util.List;

public class ReservationService {
    public static void addReservation(Reservation reservation) {
        ReservationDAO reservationDao = new Reservation_impl();
        reservationDao.addReservation(reservation);
    }

    public static void editReservation(Reservation reservation) {
        ReservationDAO reservationDao = new Reservation_impl();
        reservationDao.editReservation(reservation);
    }

    public static Reservation getReservationById(int id) {
        ReservationDAO reservationDao = new Reservation_impl();
        return reservationDao.getReservationById(id);
    }
    public static Reservation getReservationByClientId(int id) {
        ReservationDAO reservationDao = new Reservation_impl();
        return reservationDao.getReservationByClientId(id);
    }

    public static void removeReservationById(int id) {
        ReservationDAO reservationDao = new Reservation_impl();
        reservationDao.removeReservationById(id);
    }
    public static void removeReservationByApartmentId(int id) {
        ReservationDAO reservationDao = new Reservation_impl();
        reservationDao.removeReservationByApartmentId(id);
    }
    public static void removeReservationByClientId(int id) {
        ReservationDAO reservationDao = new Reservation_impl();
        reservationDao.removeReservationByClientId(id);
    }

    public static List<Reservation> getAllReservationsForApartment(int id) {
        ReservationDAO reservationDao = new Reservation_impl();
        return reservationDao.getAllReservationsForApartment(id);
    }
}
