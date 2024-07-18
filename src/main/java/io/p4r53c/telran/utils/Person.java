package io.p4r53c.telran.utils;

/**
 * A class representing a person with an id and a name.
 *
 * @author p4r53c
 * @since Class Work 8 nad HW 8
 */
public class Person implements Comparable<Person> {

    private long id;
    private String name;

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * A method to get the ID of the person.
     *
     * @return the ID of the person
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Person o) {
        return Long.compare(this.id, o.getId());
    }

    /**
     * @see io.p4r53c.telran.utils.User#equals(Object)
     * @see io.p4r53c.telran.utils.User#hashCode()
     * 
     *      But this boilerplate seems to be not needed anymore because of
     *      Comparable implementing.
     * 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        return id == other.id && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return 31 * Long.hashCode(id) + name.hashCode();
    }
}
