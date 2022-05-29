package servlets;

import com.google.gson.Gson;
import entities.Apartment_Reserv.Apartment;
import entities.Apartment_Reserv.Layout;
import entities.Apartment_Reserv.Reservation;
import entities.Apartment_Reserv.ReservationDTO;
import services.ApartmentService;
import services.ReservationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/Reservation")
public class ReservationServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Received data for reservation.");
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        Cookie[] cookie = request.getCookies();
        Gson gson = new Gson();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Layout layout = Layout.valueOf(parameterMap.get("layout")[0]);
        int occupancy = Integer.parseInt(parameterMap.get("occupancy")[0]);
        String check_in = parameterMap.get("Check-in")[0];
        String check_out = parameterMap.get("Check-out")[0];
        ReservationDTO reservationDTO = new ReservationDTO(layout, occupancy, check_in, check_out);
        Integer apartmentId = ReservationService.getApartmentByLayoutAndOccupancy(reservationDTO);

        if(apartmentId != null) {
            Apartment apartment = ApartmentService.getApartmentById(apartmentId);
            long days = ChronoUnit.DAYS.between(LocalDate.parse(check_in), LocalDate.parse(check_out));
            int bill = Integer.parseInt(String.valueOf(days * apartment.getPrice()));
            Reservation reservation = new Reservation(Integer.parseInt(cookie[0].getValue()), apartmentId, check_in, check_out, bill);
            ReservationService.addReservation(reservation);
            response.setStatus(HttpServletResponse.SC_OK);

            request.setAttribute("Apartment_number",apartment.getApNumber());
            request.setAttribute("bill",bill);
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("bill.jsp");
            requestDispatcher.forward(request, response);
        }else {
            log.info("No free apartments");
            response.getWriter().println(gson.toJson("Sorry, this time booked"));
        }
    }
}
