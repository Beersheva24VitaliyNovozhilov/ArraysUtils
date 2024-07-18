package io.p4r53c.telran.utils;

// I renamed this class to ArraysUtils to avoid using java.util.Arrays.* declarations in code. I don't like it :)
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.IntStream;

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
     * Searches for a given value in a sorted array using binary search algorithm
     * and returns the index of the first occurrence.
     *
     * @param array the sorted array
     * @param value the value to be searched for
     * @return the index of the first occurrence of the value in the array,
     *         or insertion point which the key would be inserted into the array
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

        return insertByArraycopy(array, index, value);
    }

    /**
     * Determines if only one swap is needed to sort the array.
     *
     * @param array the input array to be checked
     * @return true if only one swap is needed to sort the array, false otherwise
     */
    public static boolean isOneSwapNeeded(int[] array) {
        int[] tempArray = new int[array.length];

        System.arraycopy(array, 0, tempArray, 0, array.length);

        int firstIndex = findFirstUnsortedElementIndex(tempArray);
        int secondIndex = findSecondUnsortedElementIndex(tempArray);

        if (firstIndex < secondIndex) {
            swap(tempArray, firstIndex, secondIndex);
        }

        // KISS! :)
        return firstIndex <= secondIndex && isSorted(tempArray);
    }
    // ---------------------------------------------------------------------------
    //
    // Generics and Comparators
    //
    // ---------------------------------------------------------------------------

    /**
     * Sorts an array of elements using the provided comparator.
     *
     * @param array      the array to be sorted
     * @param comparator the comparator to determine the order of the elements
     */
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        boolean isSorted = false;

        do {
            n--;
            isSorted = true;
            for (int i = 0; i < n; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    isSorted = false;
                }
            }
        } while (!isSorted);
    }

    /**
     * Binary search method to find the index of a specific value in the array using
     * the provided comparator.
     *
     * @param array      the array to search in
     * @param value      the value to search for
     * @param comparator the comparator to determine the ordering of the elements
     * @return the index of the value if found, otherwise a negative value
     *         indicating where the value should be inserted
     */
    public static <T> int binarySearch(T[] array, T value, Comparator<T> comparator) {
        int low = 0;
        int high = array.length - 1;
        boolean isFound = false;
        int key = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int comparisonResult = comparator.compare(array[mid], value);

            if (comparisonResult == 0) {
                key = mid;
                isFound = true;
                break;
            } else if (comparisonResult < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return isFound ? key : -low - 1;
    }

    /**
     * Performs a binary search on a sorted array of elements of type T.
     * 
     * This is a type constraint that specifies that type {@code T} must implement
     * the {@code Comparable} interface and can be compared with other objects of
     * type {@code T}.
     *
     * The constraint can be extended up to a superclass by
     * {@code <T extends Comparable<? super T>>} then type {@code T} can be
     * comparable to itself or to any of its superclasses.
     * But this does not make sense in the context of the current task.
     * 
     * @param array the sorted array to be searched
     * @param value the value to be found
     * @return the index of the value if found, otherwise a negative value
     *         indicating where the value should be inserted
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T value) {
        return binarySearch(array, value, Comparator.naturalOrder());
    }

    /**
     * Performs an another binary search on the array using lambda explicit
     * predicate to determine the value.
     * 
     * This method cannot be overloaded for obvious reasons, so it has a different
     * name, but it is covered by the same tests.
     *
     * @param array the array to be searched
     * @param value the value to be searched for
     * @return the index of the value if found, otherwise a negative value
     *         indicating where the value should be inserted
     */
    public static <T extends Comparable<T>> int binarySearchByExplicitPredicate(T[] array, T value) {
        return binarySearch(array, value, Comparator.comparing(((T t) -> t)));
    }

    /**
     * Finds elements in the given array that satisfy the given predicate and
     * returns them in a new array.
     *
     * @param array     the array to search for elements
     * @param predicate the predicate used to test elements
     * @return a new array containing the elements that satisfy the predicate
     */
    public static <T> T[] findByPredicate(T[] array, Predicate<T> predicate) {
        int n = array.length;

        T[] result = Arrays.copyOf(array, 0);

        for (int i = 0; i < n; i++) {
            if (predicate.test(array[i])) {
                result = insertSorted(result, result.length, array[i]);
            }
        }
        return result;
    }

    /**
     * Removes elements from the given array based on a predicate.
     *
     * @param array     the array from which elements will be removed
     * @param predicate the predicate used to test elements
     * @return a new array containing the elements that do not satisfy the predicate
     */
    public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
        return Arrays.stream(array).filter(predicate.negate()).toArray(size -> Arrays.copyOf(array, size));
    }

    // ---------------------------------------------------------------------------
    //
    // Private methods
    //
    // ---------------------------------------------------------------------------

    /**
     * Finds the index of the first unsorted element in the given array.
     *
     * @param array the input array to search for the first unsorted element
     * @return the index of the first unsorted element, or the last index if the
     *         array is sorted
     */
    private static int findFirstUnsortedElementIndex(int[] array) {
        int n = array.length;
        int firstIndex = 0;

        while (firstIndex < n - 1 && array[firstIndex] <= array[firstIndex + 1]) {
            firstIndex++;
        }
        return firstIndex;
    }

    /**
     * Finds the index of the second unsorted element in the array.
     *
     * @param array the input array to search for the second unsorted element
     * @return the index of the second unsorted element, or the last index if the
     *         array is sorted
     */
    private static int findSecondUnsortedElementIndex(int[] array) {
        int n = array.length;
        int secondIndex = n - 1;

        while (secondIndex > 0 && array[secondIndex] >= array[secondIndex - 1]) {
            secondIndex--;
        }
        return secondIndex;
    }

    /**
     * Checks if the given array is sorted in ascending order.
     *
     * @param array the array to be checked
     * @return true if the array is sorted, false otherwise
     */
    private static boolean isSorted(int[] array) {
        // For sake of simplicity.
        // This method can be implemented by while loop but It's just a helper method.
        return array.length != 0 && IntStream.range(1, array.length).noneMatch(i -> array[i - 1] > array[i]);
    }

    /**
     * Inserts a value into a sorted array at the specified index, maintaining the
     * sorted order.
     *
     * @param array the sorted array to be inserted into
     * @param index the index at which the value should be inserted
     * @param value the value to be inserted
     * @return a new array with the value inserted at the specified index
     */
    private static <T> T[] insertSorted(T[] array, int index, T value) {
        T[] result = Arrays.copyOf(array, array.length + 1);

        System.arraycopy(array, index, result, index + 1, array.length - index);
        result[index] = value;

        return result;
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

    /**
     * Swaps the elements at indices i and j in the given array.
     *
     * @param array the array in which elements should be swapped
     * @param i     the index of the first element to be swapped
     * @param j     the index of the second element to be swapped
     * @param <T>   the type of elements in the array
     */
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}