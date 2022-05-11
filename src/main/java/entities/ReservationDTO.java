package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {
//    private int clientId;
    private Layout layout;
    private int occupancy;
    private String arrival;
    private String depature;



    public ReservationDTO() {
    }

    public ReservationDTO(int clientId, Layout layout, int occupancy, String arrival, String depature) {
//        this.clientId = clientId;
        this.layout = layout;
        this.occupancy = occupancy;
        this.arrival = arrival;
        this.depature = depature;
    }
}
