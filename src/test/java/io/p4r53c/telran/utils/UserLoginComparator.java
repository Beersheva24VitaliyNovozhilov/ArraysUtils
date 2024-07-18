package io.p4r53c.telran.utils;

import java.util.Comparator;

/**
 * Comparator for {@link User} objects by {@link User#getUserLogin()}
 *
 * @since HW 7
 *
 * @author p4r53c
 * 
 */
class UserLoginComparator implements Comparator<User> {

    @Override
    public int compare(User obj1, User obj2) {
        return obj1.getUserLogin().compareTo(obj2.getUserLogin());
    }
}
