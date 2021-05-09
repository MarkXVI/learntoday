package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuizStorage {
    private static QuizStorage quizStorage;

    static {
        try {
            quizStorage = quizStorage = new QuizStorage();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    private ArrayList<String> questionIDs = new ArrayList<>();
    DBConnection database = new ConnectionStorage().getConnection();
    private String topic;
    private int points = 0;
    private int questions = 0;

    public QuizStorage() throws SQLException {
    }

    public static QuizStorage getInstance() {
        return quizStorage;
    }

    public void add_questions(String topic) throws SQLException {
        questionIDs = database.getQuestionIDs(topic);
        questions = questionIDs.size();
    }

    public int count_questions(){
        return questionIDs.size();
    }

    public ArrayList<String> get_questionIDs() {
        return questionIDs;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        points++;
    }

    public void resetPoints() {
        points = 0;
    }

    public int getQuestions() {
        return questions;
    }
}
