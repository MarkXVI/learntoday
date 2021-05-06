import Functionality.Logic;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class logicTest {

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
    public void testCheckSufficient() throws SQLException {
        assertTrue(Logic.checkSufficientQuestions("World War 2"));
        assertFalse(Logic.checkSufficientQuestions("Test"));
    }
}
