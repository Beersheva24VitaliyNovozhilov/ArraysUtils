package io.p4r53c.telran.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ArraysTest {

    int[] numbersArray = { 8, 3, 4, 55, -15, 16, 17, 18, 19 };
    int[] emptyArray = {};

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
}
