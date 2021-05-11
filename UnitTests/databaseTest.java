import static org.junit.Assert.*;

import Model.DBConnection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class databaseTest {
    DBConnection connection;

    @Before
    public void before() throws SQLException {
        connection = new DBConnection();
    }

    @Test
    public void testGetUserInfo(){
        ArrayList<Object> user = connection.getUserinfo("test");
        assertNotNull(user);
        assertEquals(user.size(), 4);
    }

    @Test
    public void testGetTopics() throws SQLException {
        ArrayList<Object> topics = connection.getTopics("test");
        assertNotNull(topics);
        assertEquals(topics.size(), 0);
    }

    @Test
    public void testGetSubjects() throws SQLException {
        ArrayList<Object> subjects = connection.getSubjects();
        assertNotNull(subjects);
        assertEquals(subjects.size(), 3);
    }

    @Test
    public void testGetQuestionIDs() throws SQLException {
        ArrayList<String> questionIDs = connection.getQuestionIDs("test");
        assertNotNull(questionIDs);
        assertEquals(questionIDs.size(), 0);
    }

    @Test
    public void testGetAlternatives() throws SQLException {
        ArrayList<String> alternatives = connection.getAlternatives("test");
        assertNotNull(alternatives);
        assertEquals(alternatives.size(), 0);
    }

    @Test
    public void testGetText() throws SQLException {
        assertNotNull(connection.getText("World War 2"));
    }

    @Test
    public void testGetQuestion() throws SQLException {
        assertNotNull(connection.getQuestion("1"));
    }

    @Test
    public void getCourseIDsTest() throws SQLException{
        ArrayList<Integer> courseIDs = connection.getCourseIDs();
        assertNotNull(courseIDs);
    }

    @After
    public void after(){
        connection.disconnect();
    }
}