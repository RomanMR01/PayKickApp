import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * TestRunner used for running ServicesSuiteTest
 */
public class ServicesTestRunner {
    public static void main(String[] args) {
        System.out.println("Test runner started!\n");
        Result result = JUnitCore.runClasses(ServicesSuiteTest.class);

        //Print all failures
        for (Failure failure : result.getFailures()) {
            System.out.println("Failure at: " + failure.toString());
        }

        System.out.println("\nResult of services tests: " + result.wasSuccessful());
        System.out.println("Test runner ended!");
    }
}  