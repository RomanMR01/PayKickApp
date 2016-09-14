import com.epam.javalab13.service.game.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Vikno on 9/14/2016.
 * Test suite for running all services tests together
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserServiceTest.class,TeamServiceTest.class, GameServiceTest.class
})
public class ServicesSuiteTest {
}
