package Test;

import Functionality.Logic;
import Functionality.User;
import Model.DB_Connection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JUnitTest {

    Connection connection;

    @Before
    public void before() throws SQLException {
        connection = new DB_Connection().getDBConnection();
    }

    @Test
    public void check_ValidRegister() {
        Logic logic = new Logic();
        Assert.assertFalse(logic.check_ValidRegister("", "", "", "", "Choose Account Type"));
        Assert.assertFalse(logic.check_ValidRegister("test", "test", "test", "", "Student"));
        Assert.assertFalse(logic.check_ValidRegister("test", "test", "", "test", "Teacher"));
        Assert.assertFalse(logic.check_ValidRegister("test", "", "test", "test", "Student"));
        Assert.assertFalse(logic.check_ValidRegister("", "test", "test", "test", "Student"));
        Assert.assertFalse(logic.check_ValidRegister("test", "test", "test", "test", "Choose Account Type"));
        Assert.assertTrue(logic.check_ValidRegister("test", "test", "test", "test", "Student"));
    }

    @Test
    public void checkUserValues() {
        User user = new User();
        Assert.assertTrue(user.getFirstname(), true);
        Assert.assertTrue(user.getLastname(), true);
        Assert.assertTrue(user.getUsername(), true);
        Assert.assertEquals(user.getTeacher(), 0);
    }

    @Test
    public void check_login() throws SQLException {
        Assert.assertTrue(new DB_Connection().check_login("", ""));
    }

    @After
    public void after() throws SQLException {
        new DB_Connection().disconnect();
    }
}