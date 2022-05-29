package database.interfaces;

import entities.Apartment_Reserv.Apartment;
import entities.Apartment_Reserv.Reservation;
import entities.Apartment_Reserv.ReservationDTO;

import java.util.List;

public interface ReservationDAO {

    void editReservation(Reservation reservation);

    void removeReservationById(int id);

    void addReservation(Reservation reservation);

    void removeReservationByClientId(int id);

    void removeReservationByApartmentId(int id);

    Reservation getReservationById(int id);

    Reservation getReservationByClientId(int id);

    List<Reservation> getAllReservationsForApartment(int id);

    Integer getReservationsForPeriod(ReservationDTO r_DTO);

    Reservation getLastReservationById();
}
