package database.interfaces;

import entities.Apartment_Reserv.Reservation;

import java.util.List;

public interface ReservationDAO {

    void editReservation(Reservation reservation);

    void removeReservationById(int id);

    void addReservation(Reservation reservation);

    void removeReservationByClientId(int id);

    void removeReservationByRoomId(int id);

    Reservation getReservationById(int id);

    Reservation getReservationByRoomId(int id);

    Reservation getReservationByClientId(int id);

    List<Reservation> getReservationsforRoom(int id);
}
