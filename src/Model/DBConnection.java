package Model;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnection {
    private Connection connection;
    private String url = "jdbc:mysql://35.228.58.113:3306/learn2day?user=learn2dayApplication&password=ApplicationPassword";
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public DBConnection() throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    public Connection getDBConnection() {
        return connection;
    }

    public boolean checkLogin(String username, String password){
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) { // Goes through the result of the above statement
                String usernameData = resultSet.getString(1); // Takes the username value
                String passwordData = resultSet.getString(2); // Takes the password value
                if (usernameData.equals(username) && passwordData.equals(password)) { // Checks if both username and password are true
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("There was a problem checking the users.");
        }
        return false;
    }

    public void registerUser(String FirstName, String LastName, String username, String password, int Teacher) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user VALUES ('" + username + "', '" + password + "', '" + FirstName + "', '" + LastName + "', '" + Teacher + "')"); // Puts the values into the user table in the database.

        } catch (SQLException ex) {ex.printStackTrace();}
    }

    public void submitText(String topicName, String Text) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE quiz SET text = ? WHERE name = ?");
            preparedStatement.setString(1, Text);
            preparedStatement.setString(2, topicName);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {ex.printStackTrace();}
    }

    public ArrayList<Object> getUserinfo(String username) {
        ArrayList<Object> user = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.add(resultSet.getString(1));
            user.add(resultSet.getString(3));
            user.add(resultSet.getString(4));
            user.add(resultSet.getInt(5));
        } catch (SQLException ex) {ex.printStackTrace();}
        return user;
    }

    public ArrayList<Object> getSubjects(String topic) throws SQLException {
        ArrayList<Object> subjects = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM quiz WHERE topic_name = ?");
        preparedStatement.setString(1, topic);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            subjects.add(resultSet.getString(1));
        }
        return subjects;
    }

    public int checkQuestionAmount(String quiz_name) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT COUNT(question) FROM question WHERE quiz_name = ?");
        preparedStatement.setString(1, quiz_name);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public ArrayList<Object> getTopics() throws SQLException {
        ArrayList<Object> topics = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM topic");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            topics.add(resultSet.getString(1));
        }
        return topics;
    }

    public String getText(String topic) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT text FROM quiz WHERE name = ?");
        preparedStatement.setString(1, topic);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString(1);
    }

    public String getQuestion(String questionID) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT question FROM question WHERE questionID = ?");
        preparedStatement.setString(1, questionID);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String question = resultSet.getString(1);

        return question;
    }

    public ObservableList<Object> getTable(String topic) throws SQLException { // temp
        ObservableList<Object> questions = FXCollections.observableArrayList();
        preparedStatement = connection.prepareStatement("SELECT * FROM question WHERE quiz_name = ?");
        preparedStatement.setString(1, topic);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            questions.add(resultSet.getString(1));
            questions.add(resultSet.getString(2));
            questions.add(resultSet.getInt(3));
            questions.add(resultSet.getInt(4));
        }
        return questions;
    }

    public ArrayList<String> getQuestionIDs(String topic) throws SQLException {
        ArrayList<String> questionIDs = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT questionID FROM question WHERE quiz_name = ?");
        preparedStatement.setString(1, topic);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            questionIDs.add(resultSet.getString(1));
        }
        return questionIDs;
    }

    public ArrayList<String> getAlternatives(String questionID) throws SQLException {
        ArrayList<String> alternatives = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT choice FROM alternative WHERE question_questionID = ?");
        preparedStatement.setString(1, questionID);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            alternatives.add(resultSet.getString(1));
        }
        return alternatives;
    }

    public boolean checkAnswer(String answer) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT correct FROM alternative WHERE choice = ?");
        preparedStatement.setString(1, answer);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) == 1;
    }

    public void addQuiz(String question, String topic) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO question VALUES (?,?)");
            preparedStatement.setString(1, question);
            preparedStatement.setString(2, topic);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {ex.printStackTrace();}
    }

    public void addAlt(String choice, String correct, String questionID) {
        preparedStatement = connection.prepareStatement("INSERT INTO alternative VALUES (?,?,?)");
        preparedStatement.setString(1, choice);
        preparedStatement.setString(2, correct);
        preparedStatement.setString(3, questionID);
        preparedStatement.executeUpdate();
    }

    public void disconnect(){ // Disconnects from the database
        try {
            if (connection!=null) {
                connection.close();
            }
            if (statement!=null) {
                statement.close();
            }
            if (resultSet!=null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            System.out.println("failed to disconnect!");
        }
    }



}