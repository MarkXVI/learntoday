package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Connection {
    private static Connection connection;
    private static String url = "jdbc:mysql://35.228.58.113:3306/learn2day?user=root&password=root";
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    public DB_Connection() throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    public static Connection getDBConnection() throws SQLException {
        return connection;
    }

    public boolean check_login(String username, String password){
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user");
            while(resultSet.next()){ // Goes through the result of the above statement
                String usernameData = resultSet.getString(1); // Takes the username value
                String passwordData = resultSet.getString(2); // Takes the password value
                if (usernameData.equals(username) && passwordData.equals(password)){ // Checks if both username and password are true
                    return true;
                }
            }
        }catch(SQLException ex){
            System.out.println("There was a problem checking the users.");
        }
        return false;
    }

    public static void register_user(String FirstName, String LastName, String username, String password, int Teacher){
        try{
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user VALUES ('" + username + "', '" + password + "', '" + FirstName + "', '" + LastName + "', '" + Teacher + "')"); // Puts the values into the user table in the database.

        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    public List<Object> get_userinfo(String username){
        List<Object> user = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
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

    public static ArrayList<Object> get_Subjects(String topic) throws SQLException {
        ArrayList<Object> Subjects = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM quiz WHERE topic_name = ?");
        preparedStatement.setString(1, topic);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Subjects.add(resultSet.getString(1));
        }
        return Subjects;
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