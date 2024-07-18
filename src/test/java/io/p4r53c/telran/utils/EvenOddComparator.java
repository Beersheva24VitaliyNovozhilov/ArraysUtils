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
        int even1 = obj1 % 2;
        int even2 = obj2 % 2;

        int compare = Integer.compare(even1, even2);

        if (compare == 0) {
            compare = even1 == 0 ? Integer.compare(obj1, obj2) : Integer.compare(obj2, obj1);
        }
        return compare;
    }

}
