import Model.DBConnection;
import Model.QuizStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class quizStorageTest {
    DBConnection database;
    @Before
    public void before() throws SQLException {
        database = new DBConnection();
    }

    @Test
    public void getInstanceTest(){
        QuizStorage quizStorage = QuizStorage.getInstance();
        assertThat(quizStorage, instanceOf(QuizStorage.class));
    }

    @Test
    public void addQuestionsTest() throws SQLException {
        QuizStorage.getInstance().addQuestions("World War 2");
        assertEquals(QuizStorage.getInstance().getQuestionIDs(), database.getQuestionIDs("World War 2"));
    }

    @Test
    public void getQuestionIDsTest(){
        ArrayList<String> questionIDs = QuizStorage.getInstance().getQuestionIDs();
        assertThat(questionIDs, instanceOf(ArrayList.class));
    }

    @Test
    public void setTopicTest() throws NoSuchFieldException, IllegalAccessException {
        QuizStorage quizStorage = QuizStorage.getInstance();
        quizStorage.setTopic("Test");
        final Field field = quizStorage.getClass().getDeclaredField("topic");
        field.setAccessible(true);
        assertEquals(field.get(quizStorage), "Test");
    }

    @Test
    public void getTopicTest(){
        QuizStorage.getInstance().setTopic("Test2");
        assertEquals(QuizStorage.getInstance().getTopic(), "Test2");
    }

    @Test
    public void getPointsTest(){
        int points = QuizStorage.getInstance().getPoints();
        assertEquals(points, 0);
    }

    @Test
    public void addPointsTest(){
        QuizStorage.getInstance().addPoint();
        assertEquals(QuizStorage.getInstance().getPoints(), 1);
    }

    @Test
    public void resetPointsTest(){
        QuizStorage.getInstance().resetPoints();
        assertEquals(QuizStorage.getInstance().getPoints(), 0);
    }

    @Test
    public void getQuestionsTest(){
        int questions = QuizStorage.getInstance().getQuestions();
        assertEquals(questions, 0);
    }

    @After
    public void closeConnection(){
        database.disconnect();
    }
}
