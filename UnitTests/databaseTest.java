import static org.junit.Assert.*;

import Functionality.Pairs;
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
    public void testGetUserInfo() {
        ArrayList<Object> user = connection.getUserinfo("test");
        assertNotNull(user);
        assertEquals(user.size(), 4);
    }

    @Test
    public void testGetTopics() throws SQLException {
        ArrayList<String> topics = connection.getTopics("test");
        assertNotNull(topics);
        assertEquals(topics.size(), 0);
    }

    @Test
    public void testGetTopicsForSelectedSubject() throws SQLException {
        ArrayList<String> topics = connection.getTopicsForSelectedSubject("test");
        assertNotNull(topics);
        assertEquals(topics.size(), 0);
    }

    @Test
    public void testGetSubjects() throws SQLException {
        ArrayList<String> subjects = connection.getSubjects();
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
    public void testGetQuestionType() throws SQLException {
        assertNotNull(connection.getQuestionType("1"));
    }

    @Test
    public void testGetCourseIDs() throws SQLException{
        ArrayList<Integer> courseIDs = connection.getCourseIDs();
        assertNotNull(courseIDs);
    }

    @Test
    public void testGetIDForSelectedCourse() throws SQLException {
        int courseID = connection.getIDForSelectedCourse("History", "Simon");
        assertEquals(courseID, 53060);
    }

    @Test
    public void testGetUsernamesForCourse() throws SQLException {
        ArrayList<String> usernames = connection.getUsernamesForCourse(53060);
        assertNotNull(usernames);
        assertEquals(usernames.size(), 1);
    }

    @Test
    public void testGetTopicsForSelectedCourse() {
        ArrayList<String> topics = connection.getTopicsForSelectedCourse(1);
        assertNotNull(topics);
        assertEquals(topics.size(), 0);
    }

    @Test
    public void testGetCurrentUsersCourseNames() throws SQLException {
        ArrayList<String> courseNames = connection.getCurrentUsersCourseNames("testing");
        assertNotNull(courseNames);
        assertEquals(courseNames.size(), 0);
    }

    @Test
    public void testGetUserScore() {
        assertNull(connection.getUserScore("test", "test"));
    }

    @Test
    public void testGetCourseUsers() {
        ArrayList<Pairs> users = connection.getCourseStudents(1, "test");
        assertNotNull(users);
        assertEquals(users.size(), 0);
    }

    @Test
    public void testGetAllTopics() {
        ArrayList<String> allTopics = connection.getAllTopics();
        assertNotNull(allTopics);
    }

    @Test
    public void testGetAllUsernames() {
        ArrayList<String> usernames = connection.getAllUsernames();
        assertNotNull(usernames);
    }

    @After
    public void after(){
        connection.disconnect();
    }
}