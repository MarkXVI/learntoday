package Test;

import static org.junit.Assert.*;

import Functionality.Logic;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DB_Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JUnitTest {

    @Before
    public void before() throws SQLException {
        Connection connection = new DB_Connection().getDBConnection();
    }

    @Test
    public void check_ValidRegister() {
        Logic logic = new Logic();
        assertFalse(logic.check_ValidRegister("", "", "", "", "Choose Account Type"));
        assertFalse(logic.check_ValidRegister("test", "test", "test", "", "Student"));
        assertFalse(logic.check_ValidRegister("test", "test", "", "test", "Teacher"));
        assertFalse(logic.check_ValidRegister("test", "", "test", "test", "Student"));
        assertFalse(logic.check_ValidRegister("", "test", "test", "test", "Student"));
        assertFalse(logic.check_ValidRegister("test", "test", "test", "test", "Choose Account Type"));
        assertTrue(logic.check_ValidRegister("test", "test", "test", "test", "Student"));
    }

    @Test
    public void checkUserValues() {
        User user = new User();
        assertTrue(user.getFirstname(), true);
        assertTrue(user.getLastname(), true);
        assertTrue(user.getUsername(), true);
        assertEquals(user.getTeacher(), 0);
    }

    @Test
    public void check_login() throws SQLException {
        assertTrue(new DB_Connection().check_login("", ""));
    }

    @After
    public void after() throws SQLException {
        ConnectionStorage.getInstance().close_Connection();
    }
}