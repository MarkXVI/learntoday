package Test;

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

    @After
    public void after() {
        DB_Connection.closeConnection(connection);
    }
}