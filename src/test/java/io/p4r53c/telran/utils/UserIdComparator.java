package io.p4r53c.telran.utils;

import java.util.Comparator;

/**
 * Comparator for {@link User} objects by {@link User#getUserId()}
 *
 * @since HW 7
 *
 * @author p4r53c
 * 
 */
class UserIdComparator implements Comparator<User> {

    @Override
    public int compare(User obj1, User obj2) {
        return Integer.compare(obj1.getUserId(), obj2.getUserId());
    }
}
