package io.p4r53c.telran.utils;

import io.p4r53c.telran.utils.emums.ErrorString;

import java.util.function.Predicate;

/**
 * @since HW9
 */
public class CharacterRule {

    boolean isSatisfied;
    Predicate<Character> predicate;
    ErrorString errorString;

    public CharacterRule(boolean isSatisfied, Predicate<Character> predicate, ErrorString errorString) {
        this.isSatisfied = isSatisfied;
        this.predicate = predicate;
        this.errorString = errorString;
    }
}
