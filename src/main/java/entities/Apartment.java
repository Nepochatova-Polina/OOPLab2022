package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Apartment {
    private int id;
    private Layout layout;
    private ArrayList<BookedTime> bookedList;
    private int occupancy;
    private int price;

    public Apartment() {
    }

    public Apartment(int id, Layout layout, ArrayList<BookedTime> bookedList, int occupancy, int price) {
        this.id = id;
        this.layout = layout;
        this.bookedList = bookedList;
        this.occupancy = occupancy;
        this.price = price;
    }

    public Apartment(Layout layout, ArrayList<BookedTime> bookedList, int occupancy, int price) {
        this.layout = layout;
        this.bookedList = bookedList;
        this.occupancy = occupancy;
        this.price = price;
    }

    public String toString() {
        return this.id + "/n" + this.layout + "/n" + this.occupancy + "/n" + this.price;
    }


    @Getter
    @Setter
    static class BookedTime {
        private int clientId;
        private String firstDate;
        private String lastDate;

        public BookedTime(ReservationDTO reservationDTO) {
            clientId = reservationDTO.getClientId();
            firstDate = reservationDTO.getArrival();
            lastDate = reservationDTO.getDepature();
        }

        @Override
        public String toString() {
            return this.clientId + "@" + this.firstDate + "@" + this.lastDate;
        }

    }
}
