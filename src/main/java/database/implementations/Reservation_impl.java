package database.implementations;

import database.interfaces.ReservationDAO;
import entities.Apartment;
import entities.ReservationDTO;

import java.util.List;

public class Reservation_impl implements ReservationDAO {
//    private static final Logger log = Logger.getLogger(Reservation_impl.class.getName());

    private static final String EDIT_RESERVATION_QUERY =
            "UPDATE reservations SET  clientId = ?,roomId = ?, arrival = ?, depature = ?, bill = ? WHERE id = ?";
    private static final String ADD_RESERVATION_QUERY =
            "INSERT INTO reservations(id, clientId, roomId, arrival, depature, bill) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String REMOVE_RESERVATION_WITH_ID_QUERY =
            "DELETE FROM reservations WHERE reservations.id = ?";
    private static final String REMOVE_RESERVATION_WITH_ROOM_ID_QUERY =
            "DELETE FROM reservations WHERE reservations.roomId = ?";
    private static final String REMOVE_RESERVATION_WITH_CLIENT_ID_QUERY =
            "DELETE FROM reservations WHERE reservations.clientId = ?";

    private static final String GET_RESERVATION_WITH_ID_QUERY =
            "SELECT reservations.id, clientId, roomId, arrival, depature FROM reservations WHERE reservations.id = ?";
    private static final String ALL_RESERVATIONS_FOR_ROOM_QUERY =
            "SELECT reservations.id, reservations.clientId, reservations.roomId, reservations.arrival, reservations.depature, product_type.name, product_type.description FROM reservations INNER JOIN product_type ON product.product_type_id = product_type.id";

    @Override
    public void addReservation(Apartment apartment) {

    }

    @Override
    public void editReservation(ReservationDTO reservationDTO) {

    }

    @Override
    public void removeReservationWithId(int id) {

    }

    @Override
    public ReservationDTO getReservationWithId(int id) {
        return null;
    }


    @Override
    public List<Apartment> getReservationsforRoom(int id) {
        return null;
    }
}
