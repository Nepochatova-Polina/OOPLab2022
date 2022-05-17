package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LogoutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Received logout request.");
        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        Cookie loginCookie = new Cookie("user", "");
        Cookie passwordCookie = new Cookie("password", "");
        Cookie roleCookie = new Cookie("role", "");
        loginCookie.setPath("/");
        passwordCookie.setPath("/");
        roleCookie.setPath("/");
        loginCookie.setMaxAge(0);
        passwordCookie.setMaxAge(0);
        roleCookie.setMaxAge(0);
        response.addCookie(loginCookie);
        response.addCookie(passwordCookie);
        response.addCookie(roleCookie);
        response.sendRedirect("http://localhost:8080/login.jsp");
    }

}
