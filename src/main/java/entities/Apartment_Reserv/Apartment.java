package entities.Apartment_Reserv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apartment {
    private int id;
    private int apNumber;
    private Layout layout;
    private int occupancy;
    private int price;

    public Apartment(int id, int apNumber, Layout layout, int occupancy, int price) {
        this.id = id;
        this.apNumber = apNumber;
        this.layout = layout;
        this.occupancy = occupancy;
        this.price = price;
    }

    public Apartment(int apNumber, Layout layout, int occupancy, int price) {
        this.apNumber = apNumber;
        this.layout = layout;
        this.occupancy = occupancy;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", apNumber=" + apNumber +
                ", layout=" + layout +
                ", occupancy=" + occupancy +
                ", price=" + price +
                '}';
    }
}
