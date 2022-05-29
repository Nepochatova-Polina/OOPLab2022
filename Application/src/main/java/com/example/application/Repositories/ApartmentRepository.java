package com.example.application.Repositories;

import com.example.application.Entities.Apartment;
import com.example.application.Entities.Layout;
import liquibase.pro.packaged.S;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment,Long> {
    List<Apartment> findApartmentsByLayoutAndOccupancy(Layout layout, int occupancy);
    @Query(value = "select * from reservations r inner join apartments a on a.id = r.apartment_id" +
            " where a.layout = ? and a.occupancy = ? and r.check_in != ? and r.check_out != ?",
            nativeQuery = true)
     List<Apartment> findApartmentsByLayoutAndOccupancy(String layout, int occupancy, String check_in, String check_out);

    @Modifying
    @Query(value = "INSERT INTO apartments(number,layout, occupancy, price) VALUES (?, ?, ?, ?)", nativeQuery = true)
    void addApartment(Apartment apartment);
}
