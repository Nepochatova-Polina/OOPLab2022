package services;

import database.Connection_db;
import database.implementations.Apartment_impl;
import database.interfaces.ApartmentDAO;
import entities.Apartment_Reserv.Apartment;

import java.util.List;

public class ApartmentService {
    public ApartmentService() throws Exception {
//        Connection_db connection_db = new Connection_db();
    }

    public static void addApartment(Apartment apartment) {
        ApartmentDAO apartmentDao = new Apartment_impl();
        apartmentDao.addApartment(apartment);
    }

    public static void editApartment(Apartment apartment) {
        ApartmentDAO apartmentDao = new Apartment_impl();
        apartmentDao.addApartment(apartment);
    }

    public static Apartment getApartmentById(int id) {
        ApartmentDAO apartmentDao = new Apartment_impl();
        return apartmentDao.getApartmentById(id);
    }

    public static void removeApartmentById(int id) {
        ApartmentDAO apartmentDao = new Apartment_impl();
        apartmentDao.removeApartmentById(id);
    }

    public static List<Apartment> getAllApartments() {
        ApartmentDAO apartmentDao = new Apartment_impl();
        return apartmentDao.getAllApartments();
    }

//    public static void main(String[] args) throws Exception {
//        ApartmentService apartmentService = new ApartmentService();
//        List<Apartment>  x = apartmentService.getAllApartments();
//        System.out.println(x);
//    }
}

