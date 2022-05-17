package servlets;

import entities.Users.User;
import entities.Users.UserRole;
import services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RegistrationServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        log.info("Received data from the registration.");
        Map<String, String[]> parameterMap = request.getParameterMap();
        String name = parameterMap.get("username")[0];
        String password = parameterMap.get("password")[0];
        UserRole role = UserRole.fromString(parameterMap.get("role")[0]);
        User user = new User(name, password, role);
        UserService.registerUser(user);
        if(role == UserRole.CLIENT){
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/reservation.jsp");
            requestDispatcher.forward(request, response);
        }else {
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/AdminServlet");
            requestDispatcher.forward(request, response);
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/Registration");
        requestDispatcher.forward(request, response);
    }
}
