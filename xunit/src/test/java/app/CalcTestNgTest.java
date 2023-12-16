package app;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * See also:
 * <a href="https://testng.org/doc/documentation-main.html">https://testng.org/doc/documentation-main.html</a>
 * <br/>
 * <a href="https://www.baeldung.com/testng">https://www.baeldung.com/testng</a>
 * <br/>
 * <a href="https://www.baeldung.com/junit-vs-testng">JUnit4 vs TestNG</a>
 */
public class CalcTestNgTest {
    private Calc underTest;

    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        this.underTest = new Calc();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupMethod(Method testMethod) {
        // More on different TestNG options for getting meta-info:
        // https://newbedev.com/how-do-i-get-the-name-of-the-test-method-that-was-run-in-a-testng-tear-down-method
        System.out.printf("Starting a TestNG test for Calc class. Test: %s\n", testMethod.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method testMethod) {
        System.out.printf("Completing a TestNG test for Calc class. Test: %s\n\n", testMethod.getName());
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testDoubleEach() {
        // Arrange
        var input = List.of(1 ,2 ,3, 4, 20, -1, 0, 321);
        var expected = List.of(4, 6, 8, 40, -2, 0, 642, 2);

        // Act
        var actual = underTest.doubleEach(input);

        // Assert - user AssertJ library.
        // See also
        // https://assertj.github.io/doc/
        // https://www.baeldung.com/introduction-to-assertj
        assertThat(actual)
                .as("")
                .usingRecursiveComparison()
                .ignoringCollectionOrder() // Notice setting like this one!
                .isEqualTo(expected);
    }
}