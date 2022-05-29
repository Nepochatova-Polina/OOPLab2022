package com.example.application.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "number",nullable = false)
    private int apNumber;
    @Column(name = "layout", nullable = false)
    @Enumerated(EnumType.STRING)
    private Layout layout;
    @Column(name = "occupancy",nullable = false)
    private int occupancy;
    @Column(name = "price",nullable = false)
    private int price;
}
