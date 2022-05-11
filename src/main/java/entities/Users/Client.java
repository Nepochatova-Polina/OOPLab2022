package entities.Users;

import entities.Apartment_Reserv.ReservationDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Client extends User {
    private ReservationDTO reservationDTO;

    public Client(int id, String name, String password) {
        super(id, name, password, UserRole.CLIENT);
        reservationDTO = new ReservationDTO();
    }
}