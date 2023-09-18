package app;

import app.junit_rules.LogNotesRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class CalcJUnit4Test {
    /*
     * See also:
     * https://github.com/junit-team/junit4/wiki/Rules
     * https://www.baeldung.com/junit-4-rules
     */
    @Rule
    public TestName testName = new TestName();
    @Rule
    public LogNotesRule logNotesRule = new LogNotesRule();

    @Before
    public void setUp() {
        System.out.println("Starting a JUnit4 test for Calc class.");
        System.out.printf("Test name: %s\n", testName.getMethodName());

    }

    @After
    public void tearDown() {
        System.out.println("Completing a JUnit4 test for Calc class.\n");
    }

    @Test
    public void testAdd() {
    }
}