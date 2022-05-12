package database.interfaces;

import entities.Apartment_Reserv.Apartment;

import java.util.List;

public interface ApartmentDAO {

    void editApartment(Apartment apartment);

    Apartment getApartmentById(int id);

    void removeApartmentById(int id);

    void addApartment(Apartment apartment);

    List<Apartment> getAllApartments();
}
