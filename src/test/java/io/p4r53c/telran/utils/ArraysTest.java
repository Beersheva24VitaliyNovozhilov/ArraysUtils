package io.p4r53c.telran.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.Random;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArraysTest {

    private static final int N_ELEMENTS = 1000;

    private int[] numbersArray;
    private int[] sortedNumbersArray;
    private int[] emptyArray;

    // Swapper fixtures
    private int[] arrayOneSwapNeighbors;
    private int[] arrayOneSwapNonNeighbors;
    private int[] arrayMultipleSwaps;

    // Comparator fixtures
    private Integer[] sortedIntegerArray;

    private String[] stringLenSortedStringArray;
    private String[] stringArray;
    private String[] stringAsciiSortedStringArray;

    private User[] unsortedUsersArray;
    private User[] sortedByIdUsersArray;
    private User[] sortedByLoginUsersArray;

    private Person[] sortedPersonsArray;

    /**
     * Initializes the test fixture by setting up the arrays used for testing.
     *
     * This method is guaranteed an array integrity.
     */
    @BeforeEach
    void setUp() {
        numbersArray = new int[] { 8, 3, 4, 55, -15, 16, 17, 18, 19 };
        sortedNumbersArray = new int[] { -15, 3, 4, 8, 16, 17, 18, 19, 55 };

        emptyArray = new int[] {};

        arrayOneSwapNeighbors = new int[] { 2, 1, 3, 4, 5 };
        arrayOneSwapNonNeighbors = new int[] { 5, 2, 3, 1, 4 };
        arrayMultipleSwaps = new int[] { 3, 1, 5, 2, 4 };

        sortedIntegerArray = new Integer[] { -6, 1, 3, 4, 5, 7 };

        stringArray = new String[] { "lmn", "cfta", "w", "aa" };
        stringLenSortedStringArray = new String[] { "w", "aa", "lmn", "cfta" };
        stringAsciiSortedStringArray = new String[] { "aa", "cfta", "lmn", "w" };

        unsortedUsersArray = new User[] {
                new User(3, "Yuri"),
                new User(2, "User"),
                new User(1, "Admin"),
                new User(4, "Anna"),
                new User(5, "Alex")
        };

        sortedByLoginUsersArray = new User[] {
                new User(1, "Admin"),
                new User(5, "Alex"),
                new User(4, "Anna"),
                new User(2, "User"),
                new User(3, "Yuri")
        };

        sortedByIdUsersArray = new User[] {
                new User(1, "Admin"),
                new User(2, "User"),
                new User(3, "Yuri"),
                new User(4, "Anna"),
                new User(5, "Alex")
        };

        sortedPersonsArray = new Person[] {
                new Person(1, "Vasya"),
                new Person(2, "Anna"),
                new Person(3, "Yuri"),
                new Person(4, "Aaron")
        };

    }

    @Test
    void testSearchByIndex() {
        assertEquals(2, ArraysUtils.searchByIndex(numbersArray, 4));
        assertEquals(4, ArraysUtils.searchByIndex(numbersArray, -15));
        assertEquals(-1, ArraysUtils.searchByIndex(numbersArray, 100));
        assertEquals(-1, ArraysUtils.searchByIndex(emptyArray, 2));
    }

    @Test
    void testAddByCopyOf() {
        int[] expected = { 8, 3, 4, 55, -15, 16, 17, 18, 19, 20 };
        int[] actual = ArraysUtils.addByCopyOf(numbersArray, 20);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testAddByCopyOfEmptyArray() {
        int[] expected = { 8 };
        int[] actual = ArraysUtils.addByCopyOf(emptyArray, 8);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testAddByArraycopy() {
        int[] expected = { 8, 3, 4, 55, -15, 16, 17, 18, 19, 2 };
        int[] actual = ArraysUtils.addByArraycopy(numbersArray, 2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testAddByArraycopyWithEmptyArray() {
        int[] expected = { -3 };
        int[] actual = ArraysUtils.addByArraycopy(emptyArray, -3);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testInsertByArraycopyIndexZero() {
        int[] expected = { 255, 8, 3, 4, 55, -15, 16, 17, 18, 19 };
        int[] actual = ArraysUtils.insertByArraycopy(numbersArray, 0, 255);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testInsertByArraycopyIndexMiddle() {
        int[] expected = { 8, 3, 4, 55, 255, -15, 16, 17, 18, 19 };
        int[] actual = ArraysUtils.insertByArraycopy(numbersArray, 4, 255);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testInsertByArraycopyIndexLast() {
        int[] expected = { 8, 3, 4, 55, -15, 16, 17, 18, 19, 255 };
        int[] actual = ArraysUtils.insertByArraycopy(numbersArray, numbersArray.length, 255);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testInsertByArraycopyIndexEmptyArray() {
        int[] expected = { 255 };
        int[] actual = ArraysUtils.insertByArraycopy(emptyArray, emptyArray.length, 255);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testInsertByArraycopyIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            ArraysUtils.insertByArraycopy(numbersArray, numbersArray.length + 1, 255);
        });
    }

    @Test
    void testRemoveByArraycopyIndexMiddle() {
        int[] expected = { 8, 3, 4, 55, 16, 17, 18, 19 };
        int[] actual = ArraysUtils.removeByArraycopy(numbersArray, 4);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testRemoveByArraycopyIndexZero() {
        int[] expected = { 3, 4, 55, -15, 16, 17, 18, 19 };
        int[] actual = ArraysUtils.removeByArraycopy(numbersArray, 0);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testRemoveByArraycopyIndexLast() {
        int[] expected = { 8, 3, 4, 55, -15, 16, 17, 18 };
        int[] actual = ArraysUtils.removeByArraycopy(numbersArray, numbersArray.length - 1);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testRemoveByArraycopyNegativeArraySizeException() {
        assertThrows(NegativeArraySizeException.class, () -> {
            ArraysUtils.removeByArraycopy(emptyArray, 0);
        });
    }

    @Test
    void testRemoveByArraycopyArrayIndexOutOfBounds() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            ArraysUtils.removeByArraycopy(numbersArray, 9);
        });
    }

    // ---------------------------------------------------------------------------
    //
    // Sorting Methods Tests
    //
    // ---------------------------------------------------------------------------
    @Test
    void testSortByPushing() {
        ArraysUtils.sortByPushing(numbersArray);
        assertArrayEquals(sortedNumbersArray, numbersArray);
    }

    @Test
    void testRandomArray() {
        int[] randomArray = getRandomArray(N_ELEMENTS);
        int limit = randomArray.length - 1;

        ArraysUtils.sortByPushing(randomArray);

        for (int i = 0; i < limit; i++) {
            assertTrue(randomArray[i] <= randomArray[i + 1]);
        }
    }

    @Test
    void testBinarySearchKeyFound() {
        assertEquals(4, ArraysUtils.binarySearch(sortedNumbersArray, 16));
    }

    @Test
    void testBinarySearchKeyNotFound() {
        assertEquals(-10, ArraysUtils.binarySearch(sortedNumbersArray, 256));
    }

    @Test
    void testInsertSortedEmptyArray() {
        int[] expected = { 256 };
        int[] result = ArraysUtils.insertSorted(emptyArray, 256);
        assertArrayEquals(expected, result);
    }

    @Test
    void testInsertSortedValueLessThanAllElements() {
        int[] expected = { -256, -15, 3, 4, 8, 16, 17, 18, 19, 55 };
        int[] result = ArraysUtils.insertSorted(sortedNumbersArray, -256);
        assertArrayEquals(expected, result);
    }

    @Test
    void testInsertSortedValueGreaterThanAllElements() {
        int[] expected = { -15, 3, 4, 8, 16, 17, 18, 19, 55, 256 };
        int[] result = ArraysUtils.insertSorted(sortedNumbersArray, 256);
        assertArrayEquals(expected, result);
    }

    @Test
    void testInsertSortedValueInMiddleOfArray() {
        int[] expected = { -15, 3, 4, 8, 11, 16, 17, 18, 19, 55 };
        int[] result = ArraysUtils.insertSorted(sortedNumbersArray, 11);
        assertArrayEquals(expected, result);
    }

    @Test
    void testIsOneSwapNeededNeighbors() {
        assertTrue(ArraysUtils.isOneSwapNeeded(arrayOneSwapNeighbors));
    }

    void testIsOneSwapNeededNonNeighbors() {
        assertTrue(ArraysUtils.isOneSwapNeeded(arrayOneSwapNonNeighbors));
    }

    @Test
    void testIsOneSwapNeededMultipleSwaps() {
        assertFalse(ArraysUtils.isOneSwapNeeded(arrayMultipleSwaps));
    }

    private int[] getRandomArray(int n) {
        Random random = new Random();

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    // ---------------------------------------------------------------------------
    //
    // Generics and Comparators Tests
    //
    // ---------------------------------------------------------------------------
    @Test
    void testComparatorSort() {
        ArraysUtils.sort(stringArray, new AsciiComparator());
        assertArrayEquals(stringAsciiSortedStringArray, stringArray);

        ArraysUtils.sort(stringArray, new StringLenComparator());
        assertArrayEquals(stringLenSortedStringArray, stringArray);
    }

    @Test
    void testIntegerBinarySearchOwnImplComparator() {
        assertEquals(3, ArraysUtils.binarySearch(sortedIntegerArray,
                Integer.valueOf(4), new IntegerComparator()));

        assertEquals(-3, ArraysUtils.binarySearch(sortedIntegerArray,
                Integer.valueOf(2), new IntegerComparator()));

        assertEquals(-1, ArraysUtils.binarySearch(sortedIntegerArray,
                Integer.valueOf(-10), new IntegerComparator()));

        assertEquals(-7, ArraysUtils.binarySearch(sortedIntegerArray,
                Integer.valueOf(256), new IntegerComparator()));

    }

    /**
     * Just demonstrate method for using a wrapper comparator implementation.
     * 
     * @see java.util.Integer.compare
     */
    @Test
    void testIntegerSearchValueWithLibComparator() {
        assertEquals(3, ArraysUtils.binarySearch(sortedIntegerArray,
                Integer.valueOf(4), Integer::compare));

        assertEquals(-3, ArraysUtils.binarySearch(sortedIntegerArray,
                Integer.valueOf(2), Integer::compare));

        assertEquals(-1, ArraysUtils.binarySearch(sortedIntegerArray,
                Integer.valueOf(-10), Integer::compare));

        assertEquals(-7, ArraysUtils.binarySearch(sortedIntegerArray,
                Integer.valueOf(256), Integer::compare));
    }

    @Test
    void testStringBinarySearchCustomComparator() {
        assertEquals(2, ArraysUtils.binarySearch(stringLenSortedStringArray,
                "lmn", new StringLenComparator()));

        assertEquals(2, ArraysUtils.binarySearch(stringLenSortedStringArray,
                "LMN", new StringLenComparator()));

        assertEquals(1, ArraysUtils.binarySearch(stringLenSortedStringArray,
                "aa", new StringLenComparator()));

        assertEquals(-1, ArraysUtils.binarySearch(stringLenSortedStringArray,
                "", new StringLenComparator()));

        assertEquals(-5, ArraysUtils.binarySearch(stringLenSortedStringArray,
                "comparator", new StringLenComparator()));
    }

    /**
     * Just demonstrate method for using a wrapper comparator implementation.
     * 
     * @see java.util.Comparator.comparingInt
     */
    @Test
    void testStringSearchValueWithLibComparator() {
        assertEquals(2, ArraysUtils.binarySearch(stringLenSortedStringArray,
                "lmn", Comparator.comparingInt(String::length)));

        // Another way String::compareToIgnoreCase
        assertEquals(3, ArraysUtils.binarySearch(stringLenSortedStringArray,
                "CFTA", Comparator.comparingInt(String::length)));

        assertEquals(-1, ArraysUtils.binarySearch(stringLenSortedStringArray,
                "", Comparator.comparingInt(String::length)));

        assertEquals(-5, ArraysUtils.binarySearch(stringLenSortedStringArray,
                "comparator", Comparator.comparingInt(String::length)));
    }

    // Simple Object sorting and search
    @Test
    void testUserSortByIdAndLogin() {
        ArraysUtils.sort(unsortedUsersArray, new UserIdComparator());
        assertArrayEquals(sortedByIdUsersArray, unsortedUsersArray);
    }

    @Test
    void testUserSortByAsciiLogin() {
        ArraysUtils.sort(unsortedUsersArray, new UserLoginComparator());
        assertArrayEquals(sortedByLoginUsersArray, unsortedUsersArray);
    }

    @Test
    void testBinarySearchUserByLogin() {

        // userId does not matter
        User existUser1 = new User(0, "Anna");
        User existUser2 = new User(0, "User");
        User nonexistingUser1 = new User(0, "DeletedUser");
        User nonexistingUser2 = new User(0, "Aaron");

        int index = ArraysUtils.binarySearch(sortedByLoginUsersArray, existUser1, new UserLoginComparator());
        int index2 = ArraysUtils.binarySearch(sortedByLoginUsersArray, existUser2, new UserLoginComparator());
        int index3 = ArraysUtils.binarySearch(sortedByLoginUsersArray, nonexistingUser1, new UserLoginComparator());
        int index4 = ArraysUtils.binarySearch(sortedByLoginUsersArray, nonexistingUser2, new UserLoginComparator());

        assertEquals(2, index);
        assertEquals(3, index2);
        assertEquals(-4, index3);
        assertEquals(-1, index4);
    }

    @Test
    void testBinarySearchUserById() {

        // userName does not matter
        User existUser1 = new User(3, "Yuri");
        User existUser2 = new User(4, "Vasya");
        User nonexistingUser1 = new User(10, "DeletedUser");

        int index = ArraysUtils.binarySearch(sortedByIdUsersArray, existUser1, new UserIdComparator());
        int index2 = ArraysUtils.binarySearch(sortedByIdUsersArray, existUser2, new UserIdComparator());
        int index3 = ArraysUtils.binarySearch(sortedByIdUsersArray, nonexistingUser1, new UserIdComparator());

        assertEquals(2, index);
        assertEquals(3, index2);
        assertEquals(-6, index3);
    }

    // --- HW8 ---

    @Test
    void testBinarySearchWihoutExternalComparator() {
        Person person = new Person(1, "Vasya");

        assertEquals(1, ArraysUtils.binarySearch(stringAsciiSortedStringArray, "cfta"));
        assertEquals(0, ArraysUtils.binarySearch(sortedPersonsArray, person));
        assertEquals(-5, ArraysUtils.binarySearch(sortedPersonsArray, new Person(5, "DeletedUser")));

        // Test binarySearchByPredicate()
        assertEquals(1, ArraysUtils.binarySearchByExplicitPredicate(stringAsciiSortedStringArray, "cfta"));
        assertEquals(0, ArraysUtils.binarySearchByExplicitPredicate(sortedPersonsArray, person));
        assertEquals(-5, ArraysUtils.binarySearchByExplicitPredicate(sortedPersonsArray, new Person(5, "DeletedUser")));
    }

    // I did not add the following fixtures to setUp() init for convenience.
    // Separate this test class.
    @Test
    void testEvenOddSort() {
        Integer[] inputArray = new Integer[] { 7, -8, 10, -100, 13, -5, -3, -10, 99 };
        Integer[] actualResult = new Integer[] { -100, -10, -8, 10, 99, 13, 7, -3, -5 };

        ArraysUtils.sort(inputArray, new EvenOddComparator());
        assertArrayEquals(actualResult, inputArray);
    }

    @Test
    void testFindByPredicate() {
        Integer[] inputArray = new Integer[] { 7, -8, 10, -100, 13, -10, 99 };
        Integer[] actualResult = new Integer[] { 7, 13, 99 };

        assertArrayEquals(actualResult, ArraysUtils.getSubArrayByPredicate(inputArray, i -> i % 2 != 0));
        assertArrayEquals(actualResult, ArraysUtils.getSubArrayByPredicate(inputArray, new EvenOddPredicate()));
    }

    @Test
    void testRemoveIfOdd() {
        Integer[] inputArray = { 1, 2, 3, 4, 5, 6 };
        Predicate<Integer> oddPredicate = num -> num % 2 != 0;

        Integer[] expectedResult = { 2, 4, 6 };
        Integer[] actualResult = ArraysUtils.removeIfbyGetSubArrayByPredicate(inputArray, oddPredicate);
        Integer[] actualResultStream = ArraysUtils.removeIfByStreamApiAndPredicate(inputArray, oddPredicate);

        assertArrayEquals(expectedResult, actualResult);
        assertArrayEquals(actualResultStream, actualResult);
    }

    @Test
    void testRemoveIfEven() {
        Integer[] inputArray = { 1, 2, 3, 4, 5, 6 };
        Predicate<Integer> evenPredicate = num -> num % 2 == 0;

        Integer[] expectedResult = { 1, 3, 5 };
        Integer[] actualResult = ArraysUtils.removeIfbyGetSubArrayByPredicate(inputArray, evenPredicate);
        Integer[] actualResultStream = ArraysUtils.removeIfByStreamApiAndPredicate(inputArray, evenPredicate);

        assertArrayEquals(expectedResult, actualResult);
        assertArrayEquals(actualResultStream, actualResult);
    }
}
