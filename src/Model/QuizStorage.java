package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class QuizStorage {
    private static QuizStorage quizStorage;

    static {
        try {
            quizStorage = new QuizStorage();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    DBConnection database = new ConnectionStorage().getConnection();
    private ArrayList<String> questionIDs = new ArrayList<>();
    private String topic;
    private int points = 0;
    private int questions = 0;

    public QuizStorage() throws SQLException {}

    public static QuizStorage getInstance() {
        return quizStorage;
    }

    public void addQuestions(String topic) throws SQLException {
        questionIDs = database.getQuestionIDs(topic);
        questions = questionIDs.size();
    }

    public ArrayList<String> getQuestionIDs() {
        return questionIDs;
    }

    public void QuizShuffle() {
        Collections.shuffle(questionIDs);
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

    public void addQuestions(ArrayList<String> list){
        questionIDs = list;
        questions = questionIDs.size();
    }
}
