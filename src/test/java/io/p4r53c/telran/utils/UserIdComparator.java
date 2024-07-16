package io.p4r53c.telran.utils;

import java.util.Comparator;

class UserIdComparator implements Comparator<User> {

    @Override
    public int compare(User obj1, User obj2) {
        return Integer.compare(obj1.getUserId(), obj2.getUserId());
    }
}
