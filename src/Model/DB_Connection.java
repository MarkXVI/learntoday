package Model;

import java.sql.*;

public class DB_Connection {
    private String url = "jdbc:mysql://127.0.0.1:3306/mydb?user=root&password=root";
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


}
