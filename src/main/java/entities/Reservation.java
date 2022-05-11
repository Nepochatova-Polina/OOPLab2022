package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {
    private int clientId;
    private int roomId;
    private String arrival;
    private String depature;

    public Reservation(int clientId, int roomId, String arrival, String depature) {
        this.clientId = clientId;
        this.roomId = roomId;
        this.arrival = arrival;
        this.depature = depature;
    }
}
