package io.p4r53c.telran.utils;

import io.p4r53c.telran.utils.emums.ErrorString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                new CharacterRule(array -> {
                    for (char c : array) {
                        if (Character.isUpperCase(c)) {
                            return true;
                        }
                    }
                    return false;
                }, ErrorString.UPPERCASE_REQUIRED),

                new CharacterRule(array -> {
                    for (char c : array) {
                        if (Character.isLowerCase(c)) {
                            return true;
                        }
                    }
                    return false;
                }, ErrorString.LOWERCASE_REQUIRED),

                new CharacterRule(array -> {
                    for (char c : array) {
                        if (Character.isDigit(c)) {
                            return true;
                        }
                    }
                    return false;
                }, ErrorString.DIGIT_REQUIRED),

                new CharacterRule(array -> {
                    for (char c : array) {
                        if (c == '.') {
                            return true;
                        }
                    }
                    return false;
                }, ErrorString.DOT_REQUIRED)
        };

        mustNotBeRules = new CharacterRule[] {
                new CharacterRule(array -> {
                    for (char c : array) {
                        if (Character.isWhitespace(c)) {
                            return true;
                        }
                    }
                    return false;
                }, ErrorString.SPACES_NOT_ALLOWED)
        };
    }

    @Test
    void testValidArray() {
        char[] validArray = { 'a', 'n', '*', 'G', '.', '.', '1' };

        String result = ArraysUtils.matchesRules(validArray, mustBeRules, mustNotBeRules);
        assertEquals(EMPTY_STRING, result);

        assertTrue(mustBeRules[0].isSatisfied());
        assertTrue(mustBeRules[1].isSatisfied());
        assertTrue(mustBeRules[2].isSatisfied());
        assertTrue(mustBeRules[3].isSatisfied());
        assertFalse(mustNotBeRules[0].isSatisfied());
    }

    @Test
    void testArrayWithSpace() {
        char[] invalidArray = { 'a', 'n', '*', 'G', '.', '.', '1', ' ' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(ErrorString.SPACES_NOT_ALLOWED.getErrorString(), result);

        assertTrue(mustBeRules[0].isSatisfied());
        assertTrue(mustBeRules[1].isSatisfied());
        assertTrue(mustBeRules[2].isSatisfied());
        assertTrue(mustBeRules[3].isSatisfied());
        assertTrue(mustNotBeRules[0].isSatisfied());
    }

    @Test
    void testArrayWithoutUppercase() {
        char[] invalidArray = { 'a', 'n', '*', '.', '.', '1' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(ErrorString.UPPERCASE_REQUIRED.getErrorString(), result);

        assertFalse(mustBeRules[0].isSatisfied());
        assertTrue(mustBeRules[1].isSatisfied());
        assertTrue(mustBeRules[2].isSatisfied());
        assertTrue(mustBeRules[3].isSatisfied());
        assertFalse(mustNotBeRules[0].isSatisfied());
    }

    @Test
    void testArrayWithoutDigit() {
        char[] invalidArray = { 'a', 'n', '*', 'G', '.', '.' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(ErrorString.DIGIT_REQUIRED.getErrorString(), result);

        assertTrue(mustBeRules[0].isSatisfied());
        assertTrue(mustBeRules[1].isSatisfied());
        assertFalse(mustBeRules[2].isSatisfied());
        assertTrue(mustBeRules[3].isSatisfied());
        assertFalse(mustNotBeRules[0].isSatisfied());

    }

    @Test
    void testArrayWithMultipleErrors() {
        char[] invalidArray = { 'a', 'n', '*', '.', ' ' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(String.join(", ",
                ErrorString.UPPERCASE_REQUIRED.getErrorString(),
                ErrorString.DIGIT_REQUIRED.getErrorString(),
                ErrorString.SPACES_NOT_ALLOWED.getErrorString()),
                result);

        assertFalse(mustBeRules[0].isSatisfied());
        assertTrue(mustBeRules[1].isSatisfied());
        assertFalse(mustBeRules[2].isSatisfied());
        assertTrue(mustBeRules[3].isSatisfied());
        assertTrue(mustNotBeRules[0].isSatisfied());
    }

    @Test
    void testArrayWithAllErrors() {
        char[] invalidArray = { ' ', ' ' };

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(String.join(", ",
                ErrorString.UPPERCASE_REQUIRED.getErrorString(),
                ErrorString.LOWERCASE_REQUIRED.getErrorString(),
                ErrorString.DIGIT_REQUIRED.getErrorString(),
                ErrorString.DOT_REQUIRED.getErrorString(),
                ErrorString.SPACES_NOT_ALLOWED.getErrorString()),
                result);

        assertFalse(mustBeRules[0].isSatisfied());
        assertFalse(mustBeRules[1].isSatisfied());
        assertFalse(mustBeRules[2].isSatisfied());
        assertFalse(mustBeRules[3].isSatisfied());
        assertTrue(mustNotBeRules[0].isSatisfied());
    }

    @Test
    void testArrayWithAllErrorsEmptyArray() {
        char[] invalidArray = {};

        String result = ArraysUtils.matchesRules(invalidArray, mustBeRules, mustNotBeRules);
        assertEquals(String.join(", ",
                ErrorString.UPPERCASE_REQUIRED.getErrorString(),
                ErrorString.LOWERCASE_REQUIRED.getErrorString(),
                ErrorString.DIGIT_REQUIRED.getErrorString(),
                ErrorString.DOT_REQUIRED.getErrorString()),
                result);

        assertFalse(mustBeRules[0].isSatisfied());
        assertFalse(mustBeRules[1].isSatisfied());
        assertFalse(mustBeRules[2].isSatisfied());
        assertFalse(mustBeRules[3].isSatisfied());
        assertFalse(mustNotBeRules[0].isSatisfied());
    }

}