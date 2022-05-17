package entities.Apartment_Reserv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {
    private Layout layout;
    private int occupancy;
    private String check_in;
    private String check_out;



    public ReservationDTO() {
    }

    public ReservationDTO(Layout layout, int occupancy, String check_in, String check_out) {
        this.layout = layout;
        this.occupancy = occupancy;
        this.check_in = check_in;
        this.check_out = check_out;
    }
}
