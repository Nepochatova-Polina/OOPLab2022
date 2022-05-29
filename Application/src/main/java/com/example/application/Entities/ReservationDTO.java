package com.example.application.Entities;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String layout;
    private int occupancy;

//    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private String check_in;
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private String check_out;

}