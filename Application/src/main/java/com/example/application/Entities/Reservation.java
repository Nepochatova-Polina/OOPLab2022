package com.example.application.Entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "user_id", nullable = false)
    private String clientId;
    @Column(name = "apartment_id", nullable = false)
    private int apartmentId;
    @Column(name = "check_in", nullable = false)
    private String check_in;
    @Column(name = "check_out", nullable = false)
    private String check_out;
    @Column(name = "bill", nullable = false)
    private int bill;
    @Column(name = "confirmation", nullable = false)
    private boolean confirmation = false;

    public Reservation(String cliendId, int apartmentId, String check_in, String check_out, int bill) {
        this.clientId = cliendId;
        this.apartmentId = apartmentId;
        this.check_in = check_in;
        this.check_out = check_out;
        this.bill = bill;
    }
}
