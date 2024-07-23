package io.p4r53c.telran.utils;

/**
 * A functional interface for checking rules on a character array.
 * 
 * @author p4r53c
 * @since HW9
 */
@FunctionalInterface
public interface CharacterRuleChecker {

    /**
     * Verifies the given character array against the rule.
     *
     * @param array the character array to be checked
     * @return {@code true} if the array satisfies the rule, {@code false} otherwise
     */
    boolean verify(char[] array);

}
