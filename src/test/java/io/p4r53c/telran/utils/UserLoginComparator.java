package io.p4r53c.telran.utils;

import java.util.Comparator;

class UserLoginComparator implements Comparator<User> {

    @Override
    public int compare(User obj1, User obj2) {
        return obj1.getUserLogin().compareTo(obj2.getUserLogin());
    }
}