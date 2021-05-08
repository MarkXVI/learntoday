package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuizStorage {
    private static QuizStorage quizStorage = null;
    private ArrayList<String> questionIDs = new ArrayList<>();
    DBConnection database = new ConnectionStorage().getConnection();

    public QuizStorage() throws SQLException {
        quizStorage = new QuizStorage();
    }

    public static QuizStorage getInstance() {
        return quizStorage;
    }

    public void add_questions(String topic) throws SQLException {
        questionIDs = database.getQuestionIDs(topic);
    }

    public int count_questions(){
        return questionIDs.size();
    }

    public ArrayList<String> get_questionIDs() {
        return questionIDs;
    }

}
