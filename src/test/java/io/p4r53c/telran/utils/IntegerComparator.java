package io.p4r53c.telran.utils;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer obj1, Integer obj2) {
        return obj1 - obj2;
    }

}
