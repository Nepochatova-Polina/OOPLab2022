package com.example.application.Entities;


import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String layout;
    private int occupancy;
    private String check_in;
    private String check_out;

}