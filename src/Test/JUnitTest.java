package Test;

import Functionality.Logic;
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
        connection = DB_Connection.getDBConnection();
    }

    @Test
    public void checkUserCredentials() {
        Assert.assertFalse(Logic.check_ValidRegister("", "", "", ""));
        Assert.assertFalse(Logic.check_ValidRegister("test", "test", "test", ""));
        Assert.assertFalse(Logic.check_ValidRegister("test", "test", "", "test"));
        Assert.assertFalse(Logic.check_ValidRegister("test", "", "test", "test"));
        Assert.assertFalse(Logic.check_ValidRegister("", "test", "test", "test"));
        Assert.assertTrue(Logic.check_ValidRegister("test", "test", "test", "test"));
    }

    @After
    public void after() {
        DB_Connection.disconnect();
    }
}