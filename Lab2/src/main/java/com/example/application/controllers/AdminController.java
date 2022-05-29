package com.example.application.controllers;

import com.example.application.Entities.Reservation;
import com.example.application.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/administrator")
public class AdminController {
    @Autowired
    private ReservationRepository reservationRepository;

    private final ModelAndView modelAndView = new ModelAndView();
    List<Reservation> reservations = new ArrayList<>();


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/confirm")
    public ModelAndView confirmReserv(Map<String,Object> model){
        reservations = reservationRepository.findReservationsByConfirmation(false);
        model.put("reservations",reservations);
        modelAndView.setViewName("confirmation.html");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/confirm/{id}")
    public ModelAndView confirmReservation(@PathVariable int id, Map<String,Object> model){
        reservationRepository.updateReservationById(id);
        for (int i = 0; i < reservations.size(); i++) {
            if(reservations.get(i).getId() == id){
                reservations.remove(reservations.get(i));
                break;
            }
        }
        if(reservations.size() == 0){
            modelAndView.setViewName("goodbyePage.html");
        }else {
            modelAndView.setViewName("confirmation.html");
            model.put("reservations", reservations);
        }
        return modelAndView;
    }

}
