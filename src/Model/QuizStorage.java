package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuizStorage {
    private final static QuizStorage quizStorage = new QuizStorage();
    private ArrayList<String> questionIDs = new ArrayList<>();

    public QuizStorage(){}

    public static QuizStorage getInstance() {
        return quizStorage;
    }

    public void add_questions(String topic) throws SQLException {
        questionIDs = new DBConnection().getQuestionIDs(topic);
    }

    public int count_questions(){
        return questionIDs.size();
    }

    public ArrayList<String> get_questionIDs() {
        return questionIDs;
    }
}
