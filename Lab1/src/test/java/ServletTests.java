
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import servlets.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ServletTests {
    @Mock
    HttpServletRequest request = mock(HttpServletRequest.class);
    @Mock
    HttpServletResponse response = mock(HttpServletResponse.class);

    @InjectMocks
    LoginServlet loginServlet = new LoginServlet();
    @InjectMocks
    AdminServlet adminServlet = new AdminServlet();
    @InjectMocks
    LogoutServlet logoutServlet = new LogoutServlet();
    @InjectMocks
    ReservationServlet reservationServlet = new ReservationServlet();
    @InjectMocks
    RegistrationServlet registrationServlet = new RegistrationServlet();

    @BeforeEach
    void injectDependencies() {
        MockitoAnnotations.openMocks(this);
    }

    private void setRequestParametersForLogin() {
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("username", new String[]{"name"});
        parameterMap.put("password", new String[]{"password"});
        when(request.getParameterMap()).thenReturn(parameterMap);
    }

    private void setRequestParametersForRegistration() {
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("username", new String[]{"name1"});
        parameterMap.put("password", new String[]{"password1"});
        parameterMap.put("role", new String[]{"client"});
        when(request.getParameterMap()).thenReturn(parameterMap);
    }


    /* LoginServletTests*/

    @Test
    public void testLoginIncorrectDetails() throws IOException {
        setRequestParametersForLogin();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(response.getWriter()).thenReturn(new PrintWriter(outputStream));
        loginServlet.doPost(request, response);
        assertNotNull(response);
        assertTrue(outputStream.toString().isEmpty());
    }

    /* CartServletTests*/

    @Test
    public void testGetNullRequestCart() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> reservationServlet.doGet(null, response));
        assertEquals("Response/request must not be null.", exception.getMessage());
    }

    @Test
    public void testGetNullResponseCart() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> reservationServlet.doGet(request, null));
        assertEquals("Response/request must not be null.", exception.getMessage());
    }

    /* LogoutServletTests*/

    @Test
    public void testGetNullRequestLogout() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> logoutServlet.doPost(null, response));
        assertEquals("Response/request must not be null.", exception.getMessage());
    }

    @Test
    public void testGetNullResponseLogout() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> logoutServlet.doPost(request, null));
        assertEquals("Response/request must not be null.", exception.getMessage());
    }


    /* RegistrationServletTests*/

    @Test
    public void testGetNullRequestRegistration() {
        Throwable exception =
                assertThrows(
                        IllegalArgumentException.class, () -> registrationServlet.doPost(null, response));
        assertEquals("Response/request must not be null.", exception.getMessage());
    }

    @Test
    public void testGetNullResponseRegistration() {
        Throwable exception =
                assertThrows(
                        IllegalArgumentException.class, () -> registrationServlet.doPost(request, null));
        assertEquals("Response/request must not be null.", exception.getMessage());
    }

    @Test
    public void testRegistrationSuccess() {
        setRequestParametersForRegistration();
        registrationServlet.doPost(request, response);
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    /* UsersServletTests*/

    @Test
    public void testGetNullRequestUsers() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> adminServlet.doGet(null, response));
        assertEquals("Response/request must not be null.", exception.getMessage());
    }

    @Test
    public void testGetNullResponseUsers() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> adminServlet.doGet(request, null));
        assertEquals("Response/request must not be null.", exception.getMessage());
    }
}
