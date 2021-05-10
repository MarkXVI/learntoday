import Functionality.User;
import Model.UserStorage;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class userStorageTest {

    @Test
    public void getInstanceTest(){
        UserStorage userStorage = UserStorage.getInstance();
        assertThat(userStorage, instanceOf(UserStorage.class));
    }

    @Test
    public void createUserTest(){
        UserStorage.getInstance().createUser("Test", "This", "Method", 0);
        User user = UserStorage.getInstance().currentUser();
        assertThat(user, instanceOf(User.class));
    }
}
