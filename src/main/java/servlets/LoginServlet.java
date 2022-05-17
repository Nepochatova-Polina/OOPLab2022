package servlets;

import com.google.gson.Gson;
import entities.Users.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("This is login servlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Received data from logging in.");

        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        String name = parameterMap.get("username")[0];
        String password = parameterMap.get("password")[0];
        User user = UserService.findUser(name, password);
        Gson gson = new Gson();
        if (user != null) {
            log.info("Received info about the user in the servlet.");
            Cookie loginCookie = new Cookie("user", Integer.toString(user.getId()));
            Cookie passwordCookie = new Cookie("password", user.getPassword());
            Cookie roleCookie = new Cookie("role", user.getRole().toString());
            loginCookie.setPath("/");
            passwordCookie.setPath("/");
            roleCookie.setPath("/");
            response.addCookie(loginCookie);
            response.addCookie(passwordCookie);
            response.addCookie(roleCookie);

            log.info("Redirecting to " + user.getRole().toString());
            response.sendRedirect("reservation.jsp");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            log.info("User doesn't exist");
            response.getWriter().println(gson.toJson("User doesn't exist"));
        }
    }
}
