package app.junit_rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * See also:
 * <a href="https://github.com/junit-team/junit4/wiki/Rules">Rules</a>
 * <br/>
 * <a href="https://www.baeldung.com/junit-4-rules">junit-4-rules</a>
 */
public class LogNotesRule implements TestRule {
    @Override
    public Statement apply(Statement statement, Description description) {
        logInfo("[RULE] Before test: ", description);
        try {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    // Run the test.
                    statement.evaluate();
                }
            };
        } finally {
            logInfo("[RULE] Ended for ", description);
        }
    }

    private void logInfo(String msg, Description description) {
        System.out.println(msg + description.getMethodName());
    }
}
