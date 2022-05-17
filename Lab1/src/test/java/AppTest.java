
import entities.Users.*;
import database.Connection_db;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {
    @Test
    public void testAdministratorHasType() {
        Administrator administrator = new Administrator(1, "test", "password");
        assertNotNull(administrator.getRole());
        assertEquals(administrator.getRole(), UserRole.ADMINISTRATOR);
    }

    @Test
    public void testClientHasType() {
        Client client = new Client(1, "test", "password");
        assertNotNull(client.getRole());
        assertEquals(client.getRole(), UserRole.CLIENT);
    }


    @Test
    public void testPostgresqlConnectionExists() throws SQLException, InterruptedException {
        Connection_db cp = Connection_db.getC_db();
        Connection connection = cp.getConnection();
        assertNotNull(connection);
    }
}
