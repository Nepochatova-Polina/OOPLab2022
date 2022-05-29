package com.example.application.Repositories;

import com.example.application.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findReservationsByApartmentId(int id);
    List<Reservation> findReservationsByConfirmation(boolean confirmation);
    Reservation findReservationById(int id);

    @Transactional
    @Modifying
    @Query(value ="UPDATE reservations  SET confirmation = true WHERE id = ?",nativeQuery = true)
     void updateReservationById(int id);

}
