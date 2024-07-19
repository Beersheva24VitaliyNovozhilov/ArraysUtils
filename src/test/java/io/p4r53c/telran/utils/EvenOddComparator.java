package io.p4r53c.telran.utils;

import java.util.Comparator;

/**
 * Comparator that compares integers by their evenness and value in case of
 * equal evenness.
 * 
 * Сan be used {@link EvenOddPredicate} for DRY example, however it doesn't make
 * sense.
 * 
 * @author p4r53c
 * @since HW 8
 * 
 */
public class EvenOddComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer obj1, Integer obj2) {
        boolean isEven1 = obj1 % 2 == 0;
        boolean isEven2 = obj2 % 2 == 0;

        return (isEven1 == isEven2)
                ? (isEven1 ? obj1 - obj2 : obj2 - obj1) : (isEven1 ? -1 : 1);
    }
}
