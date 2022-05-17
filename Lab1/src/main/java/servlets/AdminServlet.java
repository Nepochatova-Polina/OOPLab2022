package servlets;

import com.google.gson.Gson;
import entities.Apartment_Reserv.Apartment;
import entities.Apartment_Reserv.Layout;
import entities.Apartment_Reserv.Reservation;
import entities.Apartment_Reserv.ReservationDTO;
import services.ApartmentService;
import services.ReservationService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Received data for reservation.");
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        Gson gson = new Gson();
        Reservation reservation = ReservationService.getLastReservation();
        Apartment apartment = ApartmentService.getApartmentById(reservation.getRoomId());

        response.setStatus(HttpServletResponse.SC_OK);

        request.setAttribute("apartment_number", apartment.getApNumber());
        request.setAttribute("layout", apartment.getLayout());
        request.setAttribute("occupancy", apartment.getOccupancy());
        request.setAttribute("check_in", reservation.getCheck_in());
        request.setAttribute("check_out", reservation.getCheck_out());
        request.setAttribute("bill", reservation.getBill());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("administratorConfirm.jsp");
        requestDispatcher.forward(request, response);

        log.info("No free apartments");
        response.getWriter().println(gson.toJson("Sorry, this time booked"));
    }
}

