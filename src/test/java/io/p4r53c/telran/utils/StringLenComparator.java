package io.p4r53c.telran.utils;

import java.util.Comparator;

public class StringLenComparator implements Comparator<String> {

    @Override
    public int compare(String obj1, String obj2) {
      return obj1.length() - obj2.length();
    }
}
