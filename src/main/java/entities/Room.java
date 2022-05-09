package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Room {
    private int id;
    private Layout layout;
    private ArrayList<Reservation> bookedList;
    private int occupancy;
    private int price;
}
