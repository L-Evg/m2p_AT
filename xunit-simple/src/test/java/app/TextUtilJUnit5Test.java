package app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * See also:
 * <a href="https://junit.org/junit5/docs/current/user-guide/">JUnit5</a>
 * <br/>
 * <a href="https://reflectoring.io/junit5/">https://reflectoring.io/junit5/</a>
 * <br/>
 * <a href="https://reflectoring.io/tutorial-junit5-parameterized-tests/">tutorial-junit5-parameterized-tests</a>
 * <br/>
 * <a href="https://www.baeldung.com/junit-5">https://www.baeldung.com/junit-5</a>
 */
class TextUtilJUnit5Test {

    private static TextUtil underTest; // Note, this is a potential point of issue in case of parallel runs.

    @BeforeAll
    static void setupClass() {
        underTest = new TextUtil();
    }

    static Stream<Arguments> capitalizeData() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("j", "J"),
                Arguments.of("qazXSW12", "QAZXSW12"),
                Arguments.of("uaz XSW 15", "UAZ XSW 15")
        );
    }

    @BeforeEach
    void setUp() {
        System.out.println("Starting a JUnit5 test for TextUtil class.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Completing a JUnit5 test for TextUtil class.\n");
    }

    @DisplayName("A simple test for #toLowerCase()")
    @Test
    public void testToLowerCase() {
        // Arrange
        var input = "aAaBb";
        var expected = "aaabb";

        // Act
        var actual = underTest.toLowerCase(input);

        // Assert
        assertEquals(expected, actual, "Unexpected #toLowerCase result");
    }
}