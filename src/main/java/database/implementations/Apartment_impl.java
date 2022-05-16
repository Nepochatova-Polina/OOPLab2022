package database.implementations;

import database.Connection_db;
import database.interfaces.ApartmentDAO;
import entities.Apartment_Reserv.Apartment;
import entities.Apartment_Reserv.Layout;
import entities.Apartment_Reserv.Reservation;
import entities.Users.User;
import entities.Users.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Apartment_impl implements ApartmentDAO {
    private static final Logger log = Logger.getLogger(User_impl.class.getName());

    private static final String GET_APARTMENT_BY_ID_QUERY =
            "SELECT apartments.id, layout, occupancy, price,ap_number FROM apartments WHERE apartments.id = ?";

    private static final String GET_ALL_APARTMENTS_QUERY =
            "SELECT apartments.id, layout, occupancy, price, ap_number FROM apartments";
    private static final String ADD_APARTMENT_QUERY =
            "INSERT INTO apartments(layout, occupancy, price, ap_number) VALUES (?, ?, ?, ?)";
    private static final String REMOVE_APARTMENT_WITH_ID_QUERY =
            "DELETE FROM apartments WHERE apartments.id = ?";
    private static final String EDIT_APARTMENT_QUERY =
            "UPDATE apartments SET  id = ?, layout = ?, occupancy = ?, price = ?, ap_number = ? WHERE id = ?";

    @Override
    public void addApartment(Apartment apartment) {
        if (apartment == null) {
            log.info("Cannot add apartment because it was null.");
            return;
        }
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(ADD_APARTMENT_QUERY)) {
            prepareStatement.setString(1, apartment.getLayout().toString());
            prepareStatement.setInt(2, apartment.getOccupancy());
            prepareStatement.setInt(3, apartment.getPrice());
            prepareStatement.setInt(4, apartment.getApNumber());
            if (prepareStatement.executeUpdate() <= 0) {
                log.info("Cannot add apartment.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }


    @Override
    public void editApartment(Apartment apartment) {
        if (apartment == null) {
            log.info("Cannot add apartment because it was null.");
            return;
        }
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(EDIT_APARTMENT_QUERY)) {
            prepareStatement.setInt(1, apartment.getId());
            prepareStatement.setString(2, apartment.getLayout().toString());
            prepareStatement.setInt(3, apartment.getOccupancy());
            prepareStatement.setInt(4, apartment.getPrice());
            prepareStatement.setInt(5, apartment.getApNumber());
            prepareStatement.setInt(6, apartment.getId());
            if (prepareStatement.executeUpdate() <= 0) {
                log.info("Cannot update apartment.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }

    @Override
    public Apartment getApartmentById(int id) {
        Apartment apartment = null;
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(GET_APARTMENT_BY_ID_QUERY)) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int apartmentId = resultSet.getInt(1);
                Layout layout = Layout.valueOf(resultSet.getString(2).toUpperCase());
                int occupancy = resultSet.getInt(3);
                int price = resultSet.getInt(4);
                int ap_number = resultSet.getInt(5);
                if (id == apartmentId) {
                    apartment = new Apartment(id, ap_number, layout, occupancy, price);
                }
                log.info("Found apartment with id.");
            } else {
                log.info("Couldn't find apartment with the given id.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return apartment;
    }

    @Override
    public void removeApartmentById(int id) {
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(REMOVE_APARTMENT_WITH_ID_QUERY)) {
            prepareStatement.setInt(1, id);
            if (prepareStatement.executeUpdate() <= 0) {
                log.info("Cannot remove apartment with id " + id);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
    }


    @Override
    public List<Apartment> getAllApartments() {
        log.info("Getting Apartments from the database.");
        List<Apartment> apartments = new ArrayList<>();
        Connection_db c_db = Connection_db.getC_db();
        Connection connection = c_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_APARTMENTS_QUERY)) {
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                Layout layout = Layout.valueOf(resultSet.getString(2).toUpperCase());
                int occupancy = resultSet.getInt(3);
                int price = resultSet.getInt(4);
                int ap_number = resultSet.getInt(5);
                apartments.add(new Apartment(id,ap_number,layout,occupancy,price));

            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return apartments;
    }

}
