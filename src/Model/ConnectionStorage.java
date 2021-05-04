package Model;


import java.sql.SQLException;

public class ConnectionStorage {
    private static ConnectionStorage storage;
    private DB_Connection connection;

    public ConnectionStorage() throws SQLException {
        connection = new DB_Connection();
    }

    public static ConnectionStorage getInstance() throws SQLException {
        if(storage == null) {
            storage = new ConnectionStorage();
        }
        return storage;
    }

    public DB_Connection getConnection() {
        return connection;
    }

    public void close_Connection() {
        connection.disconnect();
    }
}
