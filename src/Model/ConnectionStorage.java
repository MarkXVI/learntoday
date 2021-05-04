package Model;


import java.sql.SQLException;

public class ConnectionStorage {
    private static ConnectionStorage storage;
    private DBConnection connection;

    public ConnectionStorage() throws SQLException {
        connection = new DBConnection();
    }

    public static ConnectionStorage getInstance() throws SQLException {
        if (storage == null) {
            storage = new ConnectionStorage();
        }
        return storage;
    }

    public DBConnection getConnection() {
        return connection;
    }

    public void close_Connection() {
        connection.disconnect();
    }
}
