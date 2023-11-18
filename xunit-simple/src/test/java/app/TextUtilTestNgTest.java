package app;

import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

/**
 * See also:
 * <a href="https://testng.org/doc/documentation-main.html">Documentation-main</a>
 * <br/>
 * <a href="https://www.baeldung.com/testng">https://www.baeldung.com/testng</a>
 * <br/>
 * <a href="https://www.baeldung.com/junit-vs-testng">JUnit4 vs TestNG</a>
 */
public class TextUtilTestNgTest {
    private TextUtil underTest;

    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        this.underTest = new TextUtil();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        System.out.println("Starting a TestNG test for TextUtil class.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("Completing a TestNG test for TextUtil class.\n");
    }

    @Test(description = "A simple test for #toLowerCase()", groups = {"SMOKE", "TEXTUTIL"})
    // See also: https://testng.org/doc/documentation-main.html#test-groups - Test groups related.
    public void toLowerCaseTest() {
        // Arrange
        var input = "aAaBb";
        var expected = "aaabb";

        // Act
        var actual = underTest.toLowerCase(input);

        // Assert
        assertEquals(actual, expected, "Unexpected #toLowerCase result");
    }
}