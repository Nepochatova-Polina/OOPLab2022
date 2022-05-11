package database.interfaces;

import entities.Apartment;
import entities.ReservationDTO;

import java.util.List;

public interface ReservationDAO {

    void editReservation(ReservationDTO reservationDTO);

    void removeReservationWithId(int id);

    void addReservation(Apartment apartment);

    ReservationDTO getReservationWithId(int id);
    List<Apartment> getReservationsforRoom(int id);
}
