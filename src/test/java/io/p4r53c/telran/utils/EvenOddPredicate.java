package io.p4r53c.telran.utils;

import java.util.function.Predicate;

/**
 * A predicate that checks if a given integer is odd.
 * 
 * @author p4r53c
 * @since Class Work 8
 * 
 */
public class EvenOddPredicate implements Predicate<Integer> {

    @Override
    public boolean test(Integer obj) {
        return obj % 2 != 0;
    }
}
