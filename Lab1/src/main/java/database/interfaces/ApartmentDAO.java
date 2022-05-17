package database.interfaces;

import entities.Apartment_Reserv.Apartment;
import entities.Apartment_Reserv.Layout;

import java.util.List;

public interface ApartmentDAO {

    void editApartment(Apartment apartment);

    Apartment getApartmentById(int id);

    List<Apartment> getApartmentByLayoutAndOccupancy(Layout l, int occup);

    void removeApartmentById(int id);

    void addApartment(Apartment apartment);

    List<Apartment> getAllApartments();
}
