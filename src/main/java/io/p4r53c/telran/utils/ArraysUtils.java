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

    // ---------------------------------------------------------------------------
    //
    // Arrays sorting
    //
    // ---------------------------------------------------------------------------

    /**
     * Sorts an array in ascending order by pushing the maximum element to the end
     * in each iteration.
     *
     *
     * @param array the array to be sorted
     */
    public static void sortByPushing(int[] array) {
        int n = array.length;
        boolean isSorted = false;

        while (!isSorted) {
            n--;
            isSorted = pushMaxAtEndBySwap(array, n);
        }
    }


    /**
     * Searches for a given value in a sorted array using binary search algorithm and returns the index of the first occurrence.
     *
     * @param  array  the sorted array
     * @param  value  the value to be searched for
     * @return        the index of the first occurrence of the value in the array, 
     *                or insertion point which the key would be inserted into the array
     */
    public static int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        boolean isFound = false;
        int key = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == value) {
                isFound = true;
                key = mid;
                break;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return isFound ? key : -low - 1; 
    }

    /**
     * Inserts a value into a sorted array and returns the new sorted array.
     *
     * @param array the sorted array
     * @param value the value to be inserted
     * @return the new sorted array with the value inserted
     */
    public static int[] insertSorted(int[] array, int value) {
        int index = binarySearch(array, value);

        if (index < 0) {
            index = -(index + 1);
        }

        int[] result = new int[array.length + 1];

        System.arraycopy(array, 0, result, 0, index);
        result[index] = value;
        System.arraycopy(array, index, result, index + 1, array.length - index);

        return result;
    }

    /**
     * Determines if only one swap is needed to sort the array.
     *
     * @param array the input array to be checked
     * @return true if only one swap is needed to sort the array, false otherwise
     */
    public static boolean isOneSwapNeeded(int[] array) {
        int n = array.length;
        int a = 0;
        int b = n - 1;
        boolean isSorted = true;
        boolean isSwapped = true;

        while (a < b && array[a] < array[a + 1]) {
            a++;
        }

        while (a < b && array[b] > array[b - 1]) {
            b--;
        }

        if (array[a] > array[b]) {
            swap(array, a, b);
        }

        for (int i = 1; i < n && isSorted; i++) {
            isSorted = array[i] >= array[i - 1];
        }
        return isSwapped == a < b && array[a] <= array[b] ? isSorted : a >= b;
    }

    /**
     * Swaps elements in the given array to move the maximum element to the end in
     * each iteration.
     *
     * @param array the array to be sorted
     * @param n     the number of elements in the array to be sorted
     * @return true if no swaps were made, false otherwise
     */
    private static boolean pushMaxAtEndBySwap(int[] array, int n) {
        boolean isSwapped = true;

        for (int i = 0; i < n; i++) {
            if (array[i] > array[i + 1]) {
                isSwapped = false;
                swap(array, i, i + 1);
            }
        }
        return isSwapped;
    }

    /**
     * Swaps two elements in the given array.
     *
     * @param array the array in which elements should be swapped
     * @param i     the index of the first element to be swapped
     * @param j     the index of the second element to be swapped
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}