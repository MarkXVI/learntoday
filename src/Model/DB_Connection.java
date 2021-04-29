package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Connection {
    private static Connection connection;
    private static String url = "jdbc:mysql://35.228.58.113:3306/learn2day?user=root&password=root";
    private static Statement statement;
    private static ResultSet resultSet;

    public DB_Connection(){
    }

    public static Connection getDBConnection() throws SQLException {
        connection = DriverManager.getConnection(url);
        return connection;
    }

    public boolean check_login(String username, String password){
        try{
            getDBConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user");
            while(resultSet.next()){ // Goes through the result of the above statement
                String usernameData = resultSet.getString(1); // Takes the username value
                String passwordData = resultSet.getString(2); // Takes the password value
                if (usernameData.equals(username) && passwordData.equals(password)){ // Checks if both username and password are true
                    disconnect();
                    return true;
                }
            }
        }catch(SQLException ex){
            System.out.println("There was a problem checking the users.");
        }
        disconnect();
        return false;
    }

    public static void register_user(String FirstName, String LastName, String username, String password, int Teacher){
        try{
            getDBConnection();
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user VALUES ('" + username + "', '" + password + "', '" + FirstName + "', '" + LastName + "', '" + Teacher + "')"); // Puts the values into the user table in the database.
            disconnect();

        }catch(SQLException ex){
            System.out.println(ex);
        }
        disconnect();
    }

    public List<Object> get_userinfo(String username){
        List<Object> user = new ArrayList<>();
        try{
            getDBConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE username ='" + username + "'");
            resultSet.next();
            user.add(resultSet.getString(1));
            user.add(resultSet.getString(3));
            user.add(resultSet.getString(4));
            user.add(resultSet.getInt(5));
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return user;
    }

    public static void disconnect(){ // Disconnects from the database
        try{
            if(connection!=null){
                connection.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        }catch (SQLException ex){
            System.out.println("failed to disconnect!");
        }
    }


}