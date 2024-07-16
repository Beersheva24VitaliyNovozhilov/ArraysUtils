package io.p4r53c.telran.utils;

import java.util.Comparator;

public class AsciiComparator implements Comparator<String> {

    @Override
    public int compare(String obj1, String obj2) {
        return obj1.compareTo(obj2);
    }
}
