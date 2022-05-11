package database.interfaces;

import entities.Apartment_Reserv.Reservation;

import java.util.List;

public interface ReservationDAO {

    void editReservation(Reservation reservation);

    void removeReservationWithId(int id);

    void addReservation(Reservation reservation);

    void removeReservationWithClientId(int id);

    void removeReservationWithRoomId(int id);

    Reservation getReservationWithId(int id);
    List<Reservation> getReservationsforRoom(int id);
}
