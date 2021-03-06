package database.implementations;

import database.Connection_db;
import database.interfaces.ReservationDAO;
import entities.Apartment_Reserv.Apartment;
import entities.Apartment_Reserv.Reservation;
import entities.Apartment_Reserv.ReservationDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Reservation_impl implements ReservationDAO {
    private static final Logger log = Logger.getLogger(Reservation_impl.class.getName());

    private static final String EDIT_RESERVATION_QUERY =
            "UPDATE reservations SET  client_id = ?,room_id = ?, check_in = ?, check_out = ?, bill = ? WHERE id = ?";
    private static final String ADD_RESERVATION_QUERY =
            "INSERT INTO reservations( client_id, room_id, check_in, check_out, bill) VALUES ( ?, ?, ?, ?,?)";

    private static final String REMOVE_RESERVATION_WITH_ID_QUERY =
            "DELETE FROM reservations WHERE reservations.id = ?";
    private static final String REMOVE_RESERVATION_WITH_ROOM_ID_QUERY =
            "DELETE FROM reservations WHERE reservations.room_id = ?";
    private static final String REMOVE_RESERVATION_WITH_CLIENT_ID_QUERY =
            "DELETE FROM reservations WHERE reservations.client_id = ?";

    private static final String GET_RESERVATION_BY_ID_QUERY =
            "SELECT reservations.id, client_id, room_id, check_in, check_out, bill FROM reservations WHERE reservations.id = ?";
    private static final String GET_ALL_RESERVATION_BY_APARTMENT_ID_QUERY =
            "SELECT reservations.id, client_id, room_id, check_in, check_out, bill FROM reservations WHERE reservations.room_id = ?";
    private static final String GET_RESERVATION_BY_CLIENT_ID_QUERY =
            "SELECT reservations.id, client_id, room_id, check_in, check_out, bill FROM reservations WHERE reservations.client_id = ?";

    private static final String GET_RESERVATIONS_FOR_PERIOD_QUERY =
            " SELECT reservations.id, client_id, room_id, check_in, check_out, bill FROM reservations r " +
                    "INNER JOIN apartments a on a.id = r.room_id WHERE a.layout = ? and a.occupancy = ? and r.check_in <> ? and r.check_out <>?";

    private static final String GET_LAST_RESERVATION_QUERY =
            "SELECT reservations.id, client_id, room_id, check_in, check_out, bill FROM reservations WHERE reservations.id = (SELECT MAX(id) FROM reservations)";

    @Override
    public void addReservation(Reservation reservation) {
        if (reservation == null) {
            log.info("Cannot add product because it was null.");
            return;
        }
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(ADD_RESERVATION_QUERY)) {
            prepareStatement.setInt(1, reservation.getClientId());
            prepareStatement.setInt(2, reservation.getRoomId());
            prepareStatement.setString(3, reservation.getCheck_in());
            prepareStatement.setString(4, reservation.getCheck_out());
            prepareStatement.setInt(5, reservation.getBill());
            if (prepareStatement.executeUpdate() <= 0) {
                log.info("Cannot add reservation.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }

    @Override
    public void editReservation(Reservation reservation) {
        if (reservation == null) {
            log.info("Cannot add reservation because it was null.");
            return;
        }
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(EDIT_RESERVATION_QUERY)) {
            prepareStatement.setInt(1, reservation.getClientId());
            prepareStatement.setInt(2, reservation.getRoomId());
            prepareStatement.setString(3, reservation.getCheck_in());
            prepareStatement.setString(4, reservation.getCheck_out());
            prepareStatement.setInt(5, reservation.getBill());
            prepareStatement.setInt(6, reservation.getId());
            if (prepareStatement.executeUpdate() <= 0) {
                log.info("Cannot add reservation.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }


    @Override
    public void removeReservationById(int id) {
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(REMOVE_RESERVATION_WITH_ID_QUERY)) {
            prepareStatement.setInt(1, id);
            if (prepareStatement.executeUpdate() <= 0) {
                log.info("Cannot remove reservation with id " + id);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }

    @Override
    public void removeReservationByClientId(int id) {
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(REMOVE_RESERVATION_WITH_CLIENT_ID_QUERY)) {
            prepareStatement.setInt(1, id);
            if (prepareStatement.executeUpdate() <= 0) {
                log.info("Cannot remove reservation with id " + id);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }

    @Override
    public void removeReservationByApartmentId(int id) {
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(REMOVE_RESERVATION_WITH_ROOM_ID_QUERY)) {
            prepareStatement.setInt(1, id);
            if (prepareStatement.executeUpdate() <= 0) {
                log.info("Cannot remove reservation with id " + id);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }

    @Override
    public Reservation getReservationById(int id) {
        Reservation reservation = null;
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(GET_RESERVATION_BY_ID_QUERY)) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int reservId = resultSet.getInt(1);
                int client_id = resultSet.getInt(2);
                int room_id = resultSet.getInt(3);
                String check_in = resultSet.getString(4);
                String check_out = resultSet.getString(5);
                int bill = resultSet.getInt(6);
                if (id == reservId) {
                    reservation = new Reservation(id, client_id, room_id, check_in, check_out, bill);
                }
                log.info("Found reservation with id.");
            } else {
                log.info("Couldn't find reservation with the given id.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return reservation;
    }


    @Override
    public Reservation getReservationByClientId(int id) {
        Reservation reservation = null;
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(GET_RESERVATION_BY_CLIENT_ID_QUERY)) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int reservId = resultSet.getInt(1);
                int client_id = resultSet.getInt(2);
                int room_id = resultSet.getInt(3);
                String check_in = resultSet.getString(4);
                String check_out = resultSet.getString(5);
                int bill = resultSet.getInt(6);
                if (id == client_id) {
                    reservation = new Reservation(reservId, client_id, room_id, check_in, check_out, bill);
                }
                log.info("Found reservation with id.");
            } else {
                log.info("Couldn't find reservation with the given id.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAllReservationsForApartment(int id) {
        List<Reservation> roomReservations = new ArrayList<>();
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_RESERVATION_BY_APARTMENT_ID_QUERY)) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int reservId = resultSet.getInt(1);
                int client_id = resultSet.getInt(2);
                int room_id = resultSet.getInt(3);
                String check_in = resultSet.getString(4);
                String check_out = resultSet.getString(5);
                int bill = resultSet.getInt(6);
                if (id == room_id) {
                    roomReservations.add(new Reservation(reservId, client_id, room_id, check_in, check_out, bill));
                }
                log.info("Found reservation with id.");
            } else {
                log.info("Couldn't find reservation with the given id.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return roomReservations;
    }

    @Override
    public Integer getReservationsForPeriod(ReservationDTO r_DTO) {
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        int room_id = 0;
        try (PreparedStatement prepareStatement = connection.prepareStatement(GET_RESERVATIONS_FOR_PERIOD_QUERY)) {
            prepareStatement.setString(1, r_DTO.getLayout().toString());
            prepareStatement.setInt(2, r_DTO.getOccupancy());
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int reservId = resultSet.getInt(1);
                int client_id = resultSet.getInt(2);
                 room_id = resultSet.getInt(3);
                String check_in = resultSet.getString(4);
                String check_out = resultSet.getString(5);
                int bill = resultSet.getInt(6);

            } else {
                log.info("Couldn't find reservation with the given id.");
                return null;
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return room_id;
    }


    @Override
    public Reservation getLastReservationById() {
        Reservation reservation = null;
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(GET_LAST_RESERVATION_QUERY)) {
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int reservId = resultSet.getInt(1);
                int client_id = resultSet.getInt(2);
                int room_id = resultSet.getInt(3);
                String check_in = resultSet.getString(4);
                String check_out = resultSet.getString(5);
                int bill = resultSet.getInt(6);
                    reservation = new Reservation(reservId, client_id, room_id, check_in, check_out, bill);

                log.info("Found reservation with id.");
            } else {
                log.info("Couldn't find reservation with the given id.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return reservation;
    }
}