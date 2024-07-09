package io.p4r53c.telran.utils;

// I renamed this class to ArraysUtils to avoid using java.util.Arrays.* declarations in code. I don't like it :)
import java.util.Arrays;

/**
 * Utility methods for working with arrays.
 * 
 * @author p4r53c
 * @version 1.0
 */
public class ArraysUtils {

    private ArraysUtils() {
    }

    /**
     * Searches for a given value in an array and returns the index of the first
     * occurrence.
     *
     * @param array the array to be searched
     * @param value the value to be searched for
     * @return the index of the first occurrence of the value in the array, or -1 if
     *         the value is not found
     */
    public static int searchByIndex(int[] array, int value) {
        int index = 0;

        while (index < array.length && value != array[index]) {
            index++;
        }
        return index == array.length ? -1 : index;
    }

    /**
     * Adds a new value to the end of the array.
     *
     * @param array the original array
     * @param value the value to be added
     * @return a new array with the value added at the end
     */
    public static int[] addByCopyOf(int[] array, int value) {
        int[] result = Arrays.copyOf(array, array.length + 1);

        result[array.length] = value;
        return result;
    }

    /**
     * Creates a new array by copying the elements of the given array and appending
     * the specified value.
     *
     * @param array the original array
     * @param value the value to be added to the end of the new array
     * @return a new array with the same elements as the original array and the
     *         specified value added to the end
     */
    public static int[] addByArraycopy(int[] array, int value) {
        int[] result = new int[array.length + 1];

        System.arraycopy(array, 0, result, 0, array.length);

        result[result.length - 1] = value;
        return result;
    }

    /**
     * Inserts a new value into an array at the specified index using
     * System.arraycopy().
     *
     * @param array the original array
     * @param index the index at which the value should be inserted
     * @param value the value to be inserted into the array
     * @return a new array with the value inserted at the specified index
     */
    public static int[] insertByArraycopy(int[] array, int index, int value) {
        int[] result = new int[array.length + 1];

        System.arraycopy(array, 0, result, 0, index);
        result[index] = value;

        System.arraycopy(array, index, result, index + 1, array.length - index);
        return result;
    }

    /**
     * Removes an element from an array at the specified index by creating a new
     * array with the removed element
     * and copying the remaining elements using System.arraycopy().
     *
     * @param array the original array
     * @param index the index of the element to be removed
     * @return a new array with the element at the specified index removed
     */
    public static int[] removeByArraycopy(int[] array, int index) {
        int[] result = new int[array.length - 1];

        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        return result;
    }
}
