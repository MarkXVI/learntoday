import Functionality.User;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class userTest {
    User user;
    @Before
    public void setupTest(){
        user = new User("Test", "This", "Method", 1);
    }

    @Test
    public void testGetUsername(){
        assertEquals(user.getUsername(), "Test");
    }

    @Test
    public void testGetFirstName(){
        assertEquals(user.getFirstname(), "This");
    }

    @Test
    public void testGetLastName(){
        assertEquals(user.getLastname(), "Method");
    }

    @Test
    public void testGetTeacher(){
        assertEquals(user.getTeacher(), 1);
    }

    @Test
    public void testSetUsername(){
        user.setUsername("SetUsernameTest");
        assertEquals(user.getUsername(), "SetUsernameTest");
    }

    @Test
    public void testSetFirstName(){
        user.setFirstname("SetFirstNameTest");
        assertEquals(user.getFirstname(), "SetFirstNameTest");
    }

    @Test
    public void testSetLastName(){
        user.setLastname("SetLastNameTest");
        assertEquals(user.getLastname(), "SetLastNameTest");
    }

    @Test
    public void testSetTeacher(){
        user.setTeacher(0);
        assertEquals(user.getTeacher(), 0);
    }


}
