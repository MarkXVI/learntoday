package Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import Functionality.Logic;
import Functionality.User;
import Model.ConnectionStorage;
import Model.DBConnection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class JUnitTest {

    @Before
    public void before() throws SQLException {
        Connection connection = new DBConnection().getDBConnection();
    }

    @Test
    public void testCheckValidRegister() {
        Logic logic = new Logic();
        assertFalse(logic.checkValidRegister("", "", "", "", "Choose Account Type"));
        assertFalse(logic.checkValidRegister("test", "test", "test", "", "Student"));
        assertFalse(logic.checkValidRegister("test", "test", "", "test", "Teacher"));
        assertFalse(logic.checkValidRegister("test", "", "test", "test", "Student"));
        assertFalse(logic.checkValidRegister("", "test", "test", "test", "Student"));
        assertFalse(logic.checkValidRegister("test", "test", "test", "test", "Choose Account Type"));
        assertTrue(logic.checkValidRegister("test", "test", "test", "test", "Student"));
    }

    @Test
    public void checkUserValues() {
        User user = new User();
        assertTrue(user.getFirstname(), true);
        assertTrue(user.getLastname(), true);
        assertTrue(user.getUsername(), true);
        assertEquals(user.getTeacher(), 0);
    }

    @Test
    public void testGetUserInfo() throws SQLException {
        ArrayList<Object> user = new DBConnection().getUserinfo("test");
        assertNotNull(user);
        assertEquals(user.size(), 4);
    }

    @Test
    public void testGetSubjects() throws SQLException {
        ArrayList<Object> subjects = new DBConnection().getSubjects("test");
        assertNotNull(subjects);
        assertEquals(subjects.size(), 0);
    }

    @Test
    public void testGetTopics() throws SQLException {
        ArrayList<Object> topics = new DBConnection().getUserinfo("test");
        assertNotNull(topics);
        assertEquals(topics.size(), 4);
    }

    @Test
    public void testGetQuestionIDs() throws SQLException {
        ArrayList<String> questionIDs = new DBConnection().getQuestionIDs("test");
        assertNotNull(questionIDs);
        assertEquals(questionIDs.size(), 0);
    }

    @Test
    public void testGetAlternatives() throws SQLException {
        ArrayList<String> alternatives = new DBConnection().getAlternatives("test");
        assertNotNull(alternatives);
        assertEquals(alternatives.size(), 0);
    }

    @Test
    public void testGetText() throws SQLException {
        assertNotNull(new DBConnection().getText("test"));
    }

    @Test
    public void testGetQuestion() throws SQLException {
        assertNotNull(new DBConnection().getQuestion("test"));
    }

    @After
    public void after() throws SQLException {
        ConnectionStorage.getInstance().close_Connection();
    }
}