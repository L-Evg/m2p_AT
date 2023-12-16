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

    @Test(description = "A simple test for #capitalize()", groups = {"SMOKE", "TEXTUTIL"})
    // See also: https://testng.org/doc/documentation-main.html#test-groups - Test groups related.
    public void capitalizeTest() {
        // Arrange
        var input = "aAaBb";
        var expected = "AAABB";

        // Act
        var actual = underTest.capitalize(input);

        // Assert
        assertEquals(actual, expected, "Unexpected capitalization result");
    }

    @Test(description = "A data-driven test for #capitalize()", groups = {"TEXTUTIL"},
            dataProvider = "capitalizeData")
    public void capitalizeTest_parametrized(String inputData, String expected) {
        // Just a "sugar" to distinguish data-cases.
        System.out.printf("Test #capitalize(), input=%s%n", inputData);

        // Act
        var actual = underTest.capitalize(inputData);

        // Assert
        assertEquals(actual, expected, "Unexpected capitalization result for " + inputData);
    }

    @Test(description = "A DISABLED test for #capitalize()", groups = {"TEXTUTIL"},
            enabled = false)
    public void disabledCapitalizeTest() {
    }

    @Test(groups = {"TEXTUTIL"})
    public void intListToStringTest() {
    }

    @Test(groups = {"TEXTUTIL"})
    public void toWordsTest() {
    }

    @Test(groups = {"TEXTUTIL"})
    public void countWordsTest() {
    }

    // See also: https://testng.org/doc/documentation-main.html#parameters-dataproviders
    @DataProvider
    private static Object[][] capitalizeData() {
        // Data-case structure:
        // | Input data | Expected data |
        return new Object[][]{
                {"", ""},
                {"a", "A"},
                {"123zxc", "123ZXC"},
                {"IOP", "IOP"},
                {"qwe rty", "QWE RTY"},
                {"AsDf", "ASDF"},
        };
    }
}