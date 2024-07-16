package io.p4r53c.telran.utils;

/**
 * A class representing a user.
 * 
 * @author p4r53c
 * @since HW7
 * 
 */
class User {

    int userId;
    String userLogin;

    User(int userId, String userLogin) {
        this.userId = userId;
        this.userLogin = userLogin;
    }

    /**
     * Returns the user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns the user login.
     *
     * @return the user login
     */
    public String getUserLogin() {
        return userLogin;
    }

    // equals() MUST overrides for Comparators! 
    //This class overrides equals() and should therefore also override hashCode().
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return userId == other.userId && userLogin.equals(other.userLogin);
    }

    @Override
    public int hashCode() {
        return 31 * Integer.hashCode(userId) + userLogin.hashCode();
    }
}
