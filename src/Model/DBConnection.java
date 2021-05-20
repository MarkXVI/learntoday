package Model;

import Functionality.Pairs;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private final Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public DBConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/Learn2day?serverTimezone=UTC&user=root&password=root";
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public ArrayList<String> getTopics(String topic) throws SQLException {
        ArrayList<String> subjects = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM quiz WHERE topic_name = ?");
        preparedStatement.setString(1, topic);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            subjects.add(resultSet.getString(1));
        }
        return subjects;
    }

    public ArrayList<String> getTopicsForSelectedSubject(String subjectName) throws SQLException {
        ArrayList<String> topicsForSelectedSubject = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT name FROM quiz WHERE topic_name = ?;");
        preparedStatement.setString(1, subjectName);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            topicsForSelectedSubject.add(resultSet.getString(1));
        }
        return topicsForSelectedSubject;
    }

    public int checkQuestionAmount(String quiz_name) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT COUNT(question) FROM question WHERE quiz_name = ?");
        preparedStatement.setString(1, quiz_name);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt(1);
    }

    public ArrayList<String> getSubjects() throws SQLException {
        ArrayList<String> topics = new ArrayList<>();
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

        return resultSet.getString(1);
    }

    public String getQuestionType(String questionID) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT question_type FROM question WHERE questionID = ?");
        preparedStatement.setString(1, questionID);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getString(1);
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

    public boolean checkAnswer(String answer, String questionID) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT correct FROM alternative WHERE choice = ? AND question_questionID = ?");
        preparedStatement.setString(1, answer);
        preparedStatement.setString(2, questionID);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) == 1;
    }

    public void addQuestion(String question, String topic) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO question (question, quiz_name) VALUES (?,?)");
            preparedStatement.setString(1, question);
            preparedStatement.setString(2, topic);
            preparedStatement.executeUpdate();
        } catch (Exception ignored) {}
    }

    public void addAlt(String choice, int correct, String question) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT questionID from question WHERE question = ?");
        preparedStatement.setString(1, question);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int questionID = resultSet.getInt(1);
        try {
        preparedStatement = connection.prepareStatement("INSERT INTO alternative VALUES (?,?,?)");
        preparedStatement.setString(1, choice);
        preparedStatement.setInt(2, correct);
        preparedStatement.setInt(3, questionID);
        preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ignored) {}
    }

    public ArrayList<Integer> getCurrentUsersCourseIDs(String username) throws SQLException {
        ArrayList<Integer> currentUsersCourseIDs = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT course_courseID FROM course_has_user WHERE user_username = '" + username + "';");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            currentUsersCourseIDs.add(resultSet.getInt(1));
        }
        return currentUsersCourseIDs;
    }

    public ArrayList<String> getCurrentUsersCourseNames(ArrayList<Integer> currentUsersCourseIDs) throws SQLException {
        ArrayList<String> currentUsersCourseNames = new ArrayList<>();
        for (Integer courseID : currentUsersCourseIDs) {
            preparedStatement = connection.prepareStatement("SELECT course_name FROM course WHERE courseID = " + courseID + ";");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            currentUsersCourseNames.add(resultSet.getString(1));
        }
        return currentUsersCourseNames;
    }


    public int getIDForSelectedCourse(String courseName, String username) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT course_courseID FROM course_has_user JOIN course WHERE course_name = ? AND user_username = ? GROUP BY user_username;");
        preparedStatement.setString(1, courseName);
        preparedStatement.setString(2, username);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public ArrayList<String> getUsernamesForCourse(int courseIDForSelectedCourse) throws SQLException {
        ArrayList<String> usernames = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT user_username FROM course_has_user WHERE course_courseID = " + courseIDForSelectedCourse + ";");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usernames.add(resultSet.getString(1));
        }
        return usernames;
    }

    public ArrayList<Integer> getCourseIDs() throws SQLException {
        ArrayList<Integer> courseIDs = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT courseID FROM course");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            courseIDs.add(resultSet.getInt(1));
        }
        return courseIDs;
    }

    public void addCourse(int courseID, String courseName) throws SQLException{
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO course VALUES (?,?)");
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, courseName);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ignored) {}
    }

    public void addUserToCourse(int courseID, String username) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO course_has_user VALUES (?,?)");
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ignored) {}
    }

    public void updateUserScore(String username, String quiz, int score ){
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO user_does_quiz VALUES(?, ?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, quiz);
            preparedStatement.setInt(3, score);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public int getUserScore(String username, String quiz){
        try {
            preparedStatement = connection.prepareStatement("SELECT score FROM user_does_quiz WHERE username = ? AND quiz_name = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, quiz);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException ex) {
            return 0;
        }
    }

    public ArrayList<Pairs> getCourseUsers(int courseID, String topic){
        try{
            preparedStatement = connection.prepareStatement("Select username, score FROM course_has_user JOIN user JOIN user_does_quiz JOIN quiz WHERE user_does_quiz.user_username = username AND quiz_name = ? AND course_has_user.course_courseID = ? AND user_does_quiz.score = score group by username order by score;");
            preparedStatement.setString(1, topic);
            preparedStatement.setInt(2, courseID);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Pairs> users = new ArrayList<>();
            while(resultSet.next()){
                users.add(new Pairs(resultSet.getString(1), resultSet.getInt(2)));
            }
            return users;
        }catch(SQLException ex){
            return null;
        }
    }

    public void removeUser(int courseID, String username) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM course_has_user WHERE course_courseID = ? AND user_username = ?;");
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removeCourse(int courseID) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM course_has_user WHERE course_courseID = ?;");
            preparedStatement.setInt(1, courseID);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM course WHERE courseID = ?;");
            preparedStatement.setInt(1, courseID);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void addTopicToCourse(int courseID, String topicName) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO course_has_quiz VALUES(?, ?);");
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, topicName);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removeTopic(int courseID, String topicName) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM course_has_quiz WHERE course_courseID = ? AND quiz_name = ?;");
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, topicName);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<String> getTopicsForSelectedCourse(int courseID) {
        ArrayList<String> topicsInSelectedCourse = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT quiz_name FROM course_has_quiz WHERE course_courseID = ?");
            preparedStatement.setInt(1, courseID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                topicsInSelectedCourse.add(resultSet.getString(1));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return topicsInSelectedCourse;
    }

    public void disconnect() { // Disconnects from the database
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
            System.out.println("Failed to disconnect!");
        }
    }
}