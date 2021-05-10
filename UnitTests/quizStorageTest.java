import Model.ConnectionStorage;
import Model.DBConnection;
import Model.QuizStorage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

public class quizStorageTest {
    DBConnection database;
    @Before
    public void before() throws SQLException {
        database = ConnectionStorage.getInstance().getConnection();
    }

    @Test
    public void getInstanceTest(){
        QuizStorage quizStorage = QuizStorage.getInstance();
        assertThat(quizStorage, instanceOf(QuizStorage.class));
    }

    @Test
    public void addQuestionsTest() throws SQLException {
        QuizStorage.getInstance().add_questions("World War 2");
        assertEquals(QuizStorage.getInstance().get_questionIDs(), database.getQuestionIDs("World War 2"));
    }

    @Test
    public void getQuestionIDsTest(){
        ArrayList questionIDs = QuizStorage.getInstance().get_questionIDs();
        assertThat(questionIDs, instanceOf(ArrayList.class));
    }
}
