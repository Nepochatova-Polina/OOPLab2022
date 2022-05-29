package com.example.application.controllers;

import com.example.application.Entities.Apartment;
import com.example.application.Entities.Reservation;
import com.example.application.Entities.ReservationDTO;
import com.example.application.Repositories.ApartmentRepository;
import com.example.application.Repositories.ReservationRepository;
import com.example.application.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private UserRepository userRepository;

    private final ModelAndView modelAndView = new ModelAndView();

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/reservation")
    @ResponseBody
    public ModelAndView reservation(){
        modelAndView.setViewName("reservation.html");
        return modelAndView;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/reservation")
    @ResponseBody
    public ModelAndView addNewReservation(ReservationDTO reservationDTO, Map<String, Object> model){
        String client_id = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Apartment> apartments =
                apartmentRepository.findApartmentsByLayoutAndOccupancy( reservationDTO.getLayout(),
                        reservationDTO.getOccupancy(),
                        reservationDTO.getCheck_in(),
                        reservationDTO.getCheck_out());
        if(apartments.size() > 0) {
            long days = ChronoUnit.DAYS.between(LocalDate.parse(reservationDTO.getCheck_in()), LocalDate.parse(reservationDTO.getCheck_out()));
            int bill = Integer.parseInt(String.valueOf(days * apartments.get(0).getPrice()));
            Reservation reservation = new Reservation(client_id,apartments.get(0).getId(),reservationDTO.getCheck_in(),reservationDTO.getCheck_out(),bill);
            reservationRepository.save(reservation);
            model.put("Apartment_number",apartments.get(0).getApNumber());
            model.put("bill",bill);
        }else {
            model.put("message", "There is no free room. Please, make another reservation");
        }
        modelAndView.setViewName("bill.html");
        return modelAndView;
    }


}
