import Model.ConnectionStorage;
import Model.DBConnection;
import org.junit.After;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class connectionStorageTest {
    @Test
    public void getInstanceTest() throws SQLException {
        ConnectionStorage connectionStorage = ConnectionStorage.getInstance();
        assertThat(connectionStorage, instanceOf(ConnectionStorage.class));
    }

    @Test
    public void getConnectionTest() throws SQLException{
        DBConnection database = ConnectionStorage.getInstance().getConnection();
        assertThat(database, instanceOf(DBConnection.class));
    }

    @After
    public void closeConnectionTest() throws SQLException {
        ConnectionStorage.getInstance().close_Connection();
    }
}
