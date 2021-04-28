package Model;

import java.sql.*;

public class DB_Connection {
    private String url = "jdbc:mysql://127.0.0.1:3306/learn2day?user=root&password=root";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public DB_Connection(){
    }

    public boolean check_login(String username, String password){
        try{
            connection = DriverManager.getConnection(url);
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

    public void register_user(String FirstName, String LastName,String username, String password){
        try{
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user VALUES ('" + username + "', '" + password + "', '" + FirstName + "', '" + LastName + "')"); // Puts the values into the user table in the database.
            disconnect();

        }catch(SQLException ex){
            System.out.println("There was a problem registering user.");
        }
        disconnect();
    }

    public void disconnect(){ // Disconnects from the database
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