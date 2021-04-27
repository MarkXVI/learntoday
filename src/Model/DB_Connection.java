package Model;

import java.sql.*;

public class DB_Connection {
    private String url = "jdbc:mysql://127.0.0.1:3306/learn2day?user=root&password=root";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public DB_Connection(){
        try{
            connection = DriverManager.getConnection(url);
        }catch(SQLException exception){
            System.out.println(exception.fillInStackTrace());
        }
    }

    public boolean check_login(String username, String password){
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user");
            while(resultSet.next()){
                String usernameData = resultSet.getString(1);
                String passwordData = resultSet.getString(2);
                if (usernameData.equals(username) && passwordData.equals(password)){
                    return true;
                    }
                }
            }catch(SQLException ex){
            System.out.println(ex.fillInStackTrace());
        }
        return false;
    }

    public void register_user(String username, String password){
        try{
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user VALUES ('" + username + "', '" + password + "')");

        }catch(SQLException ex){
            System.out.println(ex.fillInStackTrace());
        }
    }

    public void disconnect(){
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