package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {
    private int clientId;
    private String firstDate;
    private String lastDate;

    public Reservation(int clientId, String firstDate, String lastDate) {
        this.clientId = clientId;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }
}
