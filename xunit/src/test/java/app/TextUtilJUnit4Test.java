package app;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import static org.junit.Assert.*;

/**
 * See also:
 * <a href="https://junit.org/junit4/">JUnit4</a>
 * <br/>
 *
 */
public class TextUtilJUnit4Test {
    private static TextUtil underTest; // Note, this is a potential point of issue in case of parallel runs.

    @BeforeClass
    public static void setupClass() {
        underTest = new TextUtil();
    }

    @Test
    public void testCapitalize() {
        // Arrange
        var input = "aAaBb";
        var expected = "AAABB";

        // Act
        var actual = underTest.capitalize(input);

        // Assert
        assertEquals("Unexpected capitalization result", expected, actual);
    }

    @Test
    @Ignore("Just because we can!")
    void testCapitalize_ignored() {
    }

    @Test
    public void testIntListToString() {
    }

    @Test
    public void testToWords() {
    }

    @Test
    public void testCountWords() {
    }
}