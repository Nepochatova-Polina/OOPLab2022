package servlets;

import entities.Users.User;
import services.UserService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;
import com.google.gson.Gson;
import java.util.logging.Logger;

@WebServlet( "/Login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());
    @Override
    public void init() {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("Received data from logging in.");

        if (response == null || request == null) {
            throw new IllegalArgumentException("Response/request must not be null.");
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        String name = parameterMap.get("username")[0];
        String password = parameterMap.get("password")[0];
        User user = UserService.findUser(name, password);
        if(user != null) {
            Gson gson = new Gson();
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
            response.getWriter().println(gson.toJson(user.getRole().toString()));
        }else {
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/Register");
            requestDispatcher.forward(request, response);
        }
    }
}
