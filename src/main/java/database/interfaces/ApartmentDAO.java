package database.interfaces;

import entities.Apartment;
import entities.Layout;

import java.util.List;

public interface ApartmentDAO {

    void editApartment(Apartment apartment);

    Apartment getApartmentWithId(int id);

    void removeApartmentWithId(int id);

    void addApartment(Apartment apartment);

    List<Apartment> getApartments();
}
