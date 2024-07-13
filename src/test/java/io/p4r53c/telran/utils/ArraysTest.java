package io.p4r53c.telran.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

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

    // ------
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

    // ------

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

}
