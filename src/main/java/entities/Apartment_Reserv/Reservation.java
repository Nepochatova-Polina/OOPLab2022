package entities.Apartment_Reserv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {
    private int id;
    private int clientId;
    private int roomId;
    private String check_in;
    private String check_out;
    private int bill;
    public Reservation(int id, int clientId, int roomId, String check_in, String check_out, int bill) {
        this.id = id;
        this.clientId = clientId;
        this.roomId = roomId;
        this.check_in = check_in;
        this.check_out = check_out;
        this.bill = bill;
    }

    public Reservation(int clientId, int roomId, String check_in, String check_out, int bill) {
        this.clientId = clientId;
        this.roomId = roomId;
        this.check_in = check_in;
        this.check_out = check_out;
        this.bill = bill;
    }
}
