package io.p4r53c.telran.utils;

import io.p4r53c.telran.utils.emums.ErrorString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @since HW9
 */
class RuleMatcherTest {

    private static CharacterRule[] mustBeRules;
    private static CharacterRule[] mustNotBeRules;

    private static final String EMPTY_STRING = "";

    @BeforeEach
    public void setUp() {
        mustBeRules = new CharacterRule[] {
                new CharacterRule(true, Character::isUpperCase, ErrorString.UPPERCASE_REQUIRED),
                new CharacterRule(true, Character::isLowerCase, ErrorString.LOWERCASE_REQUIRED),
                new CharacterRule(true, Character::isDigit, ErrorString.DIGIT_REQUIRED),
                new CharacterRule(true, ch -> ch == '.', ErrorString.DOT_REQUIRED)
        };

        mustNotBeRules = new CharacterRule[] {
                new CharacterRule(false, Character::isWhitespace, ErrorString.SPACES_NOT_ALLOWED)
        };
    }

    @Test
    void testValidArray() {
        char[] validArray = { 'a', 'n', '*', 'G', '.', '.', '1' };

        String result = ArraysUtils.matchesRules(validArray, mustBeRules, mustNotBeRules);
        assertEquals(EMPTY_STRING, result);
    }

    @Test
    void testArrayWithSpace() {
        char[] invalidArray = { 'a', 'n', '*', 'G', '.', '.', '1', ' ' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(ErrorString.SPACES_NOT_ALLOWED.getErrorMessage(), result);
    }

    @Test
    void testArrayWithoutUppercase() {
        char[] invalidArray = { 'a', 'n', '*', '.', '.', '1' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(ErrorString.UPPERCASE_REQUIRED.getErrorMessage(), result);
    }

    @Test
    void testArrayWithoutDigit() {
        char[] invalidArray = { 'a', 'n', '*', 'G', '.', '.' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(ErrorString.DIGIT_REQUIRED.getErrorMessage(), result);
    }

    @Test
    void testArrayWithMultipleErrors() {
        char[] invalidArray = { 'a', 'n', '*', '.', ' ' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(String.join(", ",
                ErrorString.SPACES_NOT_ALLOWED.getErrorMessage(),
                ErrorString.UPPERCASE_REQUIRED.getErrorMessage(),
                ErrorString.DIGIT_REQUIRED.getErrorMessage()),
                result);
    }

    @Test
    void testArrayWithAllErrors() {
        char[] invalidArray = { ' ', ' ' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(String.join(", ",
                ErrorString.SPACES_NOT_ALLOWED.getErrorMessage(),
                ErrorString.UPPERCASE_REQUIRED.getErrorMessage(),
                ErrorString.LOWERCASE_REQUIRED.getErrorMessage(),
                ErrorString.DIGIT_REQUIRED.getErrorMessage(),
                ErrorString.DOT_REQUIRED.getErrorMessage()),
                result);
    }

    @Test
    void testArrayWithAllErrorsEmptyArray() {
        char[] invalidArray = {};

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(String.join(", ",
                ErrorString.UPPERCASE_REQUIRED.getErrorMessage(),
                ErrorString.LOWERCASE_REQUIRED.getErrorMessage(),
                ErrorString.DIGIT_REQUIRED.getErrorMessage(),
                ErrorString.DOT_REQUIRED.getErrorMessage()),
                result);
    }
}