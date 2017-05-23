import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Arrays;
import java.util.List;

public class TestRunner {

    private static final List<Class> classes = Arrays.asList(GithubClientTests
            .class, LangCountTests.class, MainTests.class);

    public static void main(String[] args) {

        for (Class c : classes) {
            runTests(c);
        }

    }

    private static void runTests(Class c) {
        Result result = JUnitCore.runClasses(c);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.getFailureCount() > 0) {
            System.out.println(c.toString() + "failed " + result
                    .getFailureCount() + "tests");
        } else {
            System.out.println(c.toString() + " passed all tests ");
        }
        System.out.println("----------------------------------");
    }
}
