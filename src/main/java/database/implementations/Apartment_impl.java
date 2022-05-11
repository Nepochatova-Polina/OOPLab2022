package database.implementations;

import database.Connection_db;
import database.interfaces.ApartmentDAO;
import entities.Apartment;

import java.util.List;

public class Apartment_impl implements ApartmentDAO {


    @Override
    public void editApartment(Apartment apartment) {

    }

    @Override
    public Apartment getApartmentWithId(int id) {
        return null;
    }

    @Override
    public void removeApartmentWithId(int id) {

    }

    @Override
    public void addApartment(Apartment apartment) {

    }

    @Override
    public List<Apartment> getApartments() {
        return null;
    }

    public static void main(String[] args) throws Exception {
        Connection_db l = new Connection_db();
//        User_impl x = new User_impl();
//        x.getUser(6856);
    }
}
