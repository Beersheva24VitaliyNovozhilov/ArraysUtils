package io.p4r53c.telran.utils;

import io.p4r53c.telran.utils.emums.ErrorString;

/**
 * A class representing a rule for a character array.
 *
 * The class holds a {@link CharacterRuleChecker} and an {@link ErrorString}. 
 * The {@link CharacterRuleChecker} is used to check if the character array satisfies the rule.
 * The {@link ErrorString} is an error message associated with the rule.
 * 
 * @author p4r53c
 * @see CharacterRuleChecker
 * @see ErrorString
 * @since HW9
 */
public class CharacterRule {

    // All rules are initialized as not satisfied by default.
    boolean isSatisfied = false;

    ErrorString errorString;

    CharacterRuleChecker characterRuleChecker;

    public CharacterRule(CharacterRuleChecker characterRuleChecker, ErrorString errorString) {
        this.characterRuleChecker = characterRuleChecker;
        this.errorString = errorString;
    }

    /**
     * Returns the satisfaction status of the character rule.
     *
     * @return the satisfaction status of the character rule
     */
    public boolean isSatisfied() {
        return isSatisfied;
    }

    /**
     * Sets the satisfaction status of the character rule.
     *
     * @param isSatisfied the new satisfaction status
     */
    public void setSatisfied(boolean isSatisfied) {
        this.isSatisfied = isSatisfied;
    }

    /**
     * Retrieves the error string associated with this character rule.
     * 
     * Used as ENUM for convenience
     *
     * @return the error string
     */
    public String getErrorString() {
        return errorString.getErrorString();
    }

    /**
     * Verifies the given character array using the character rule checker.
     *
     * @param array the character array to be verified
     * @return true if the character array satisfies the character rule,
     *         false otherwise
     */
    public boolean verify(char[] array) {
        return characterRuleChecker.verify(array);
    }
}
