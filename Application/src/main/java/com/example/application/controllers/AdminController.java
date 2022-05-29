package com.example.application.controllers;

import com.example.application.Entities.Reservation;
import com.example.application.Repositories.ReservationRepository;
import com.example.application.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ReservationRepository reservationRepository;

    private final ModelAndView modelAndView = new ModelAndView();
    List<Reservation> reservations = new ArrayList<>();


    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/confirm")
    public ModelAndView confirmReserv(Map<String,Object> model){
        reservations = reservationRepository.findReservationsByConfirmation(false);
        model.put("reservations",reservations);
        modelAndView.setViewName("confirmation.html");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/confirm/{id}")
    public ModelAndView confirmReservation(@PathVariable int id, Map<String,Object> model){
        reservationRepository.updateReservationById(id);
        for (int i = 0; i < reservations.size(); i++) {
            if(reservations.get(i).getId() == id){
                reservations.remove(reservations.get(i));
                break;
            }
        }
        model.put("reservations",reservations);
        modelAndView.setViewName("confirmation.html");
        return modelAndView;
    }

}
