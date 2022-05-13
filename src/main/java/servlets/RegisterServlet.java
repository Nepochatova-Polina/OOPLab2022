package servlets;

import entities.Users.User;
import entities.Users.UserRole;
import services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RegisterServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/Register");
        requestDispatcher.forward(request, response);
    }

}
