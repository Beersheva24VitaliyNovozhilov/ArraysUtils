package io.p4r53c.telran.utils;

import io.p4r53c.telran.utils.emums.ErrorString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
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
     * Without changing of signature.
     * 
     * @param array the array to be searched
     * @param value the value to be found
     * @return the index of the value if found, otherwise a negative value
     *         indicating where the value should be inserted
     */
    public static <T> int binarySearch(T[] array, T value) {
        return binarySearch(array, value, Comparator.comparing(T::toString)); // No classcasting, but bad performance
        /*
         * binarySearch(array, value, (t1, t2) -> ((Comparable<T>) t1).compareTo(t2)); Same as above, classcasting
         * binarySearch(array, value, (Comparator<T>) Comparator.naturalOrder()); Same as above, classcasting
         * 
         * Actually, standard implementation do classcasting, and it is costless operation.
         * So in this case from efficiency POV more preferrable to use casting to Comparator.
         * 
         */
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
     * Perhaps, this method should be called "findByPredicate" because it changes
     * input array.
     *
     * @param array     the array to search for elements
     * @param predicate the predicate used to test elements
     * @return a new array containing the elements that satisfy the predicate
     */
    public static <T> T[] getSubArrayByPredicate(T[] array, Predicate<T> predicate) {
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
     *
     * @param array     the array from which elements will be removed
     * @param predicate the predicate used to test elements
     * @return a new array containing the elements that do not satisfy the predicate
     */
    public static <T> T[] removeIfbyGetSubArrayByPredicate(T[] array, Predicate<T> predicate) {
        return getSubArrayByPredicate(array, predicate.negate());
    }

    /**
     * Removes elements from the given array based on a predicate.
     * Stream API is used.
     *
     * @param array     the array from which elements will be removed
     * @param predicate the predicate used to test elements
     * @return a new array containing the elements that do not satisfy the predicate
     */
    public static <T> T[] removeIfByStreamApiAndPredicate(T[] array, Predicate<T> predicate) {
        return Arrays.stream(array).filter(predicate.negate()).toArray(size -> Arrays.copyOf(array, size));
    }

    /**
     * Checks if the given character array satisfies the rules defined in the given
     * arrays of
     * must-be and must-not-be rules. Returns a string containing error messages for
     * any rules
     * that are not satisfied.
     *
     * @param array          the character array to check
     * @param mustBeRules    the array of must-be rules to check against the
     *                       character array
     * @param mustNotBeRules the array of must-not-be rules to check against the
     *                       character array
     * @return a string containing error messages for any rules that are not
     *         satisfied
     */
    public static String matchesRules(char[] array, CharacterRule[] mustBeRules, CharacterRule[] mustNotBeRules) {
        boolean[] mustBeSatisfied = new boolean[mustBeRules.length];
        StringBuilder errorMessages = new StringBuilder();
        Set<ErrorString> addedErrors = new HashSet<>();

        checkMustBeRules(array, mustBeRules, mustBeSatisfied);
        checkMustNotBeRules(array, mustNotBeRules, addedErrors, errorMessages);
        accumulateErrors(mustBeRules, mustBeSatisfied, addedErrors, errorMessages);

        return errorMessages.toString();
    }



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
    public static boolean isSorted(int[] array) {
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
    public static <T> T[] insertSorted(T[] array, int index, T value) {
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
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // -- HW 9 --

    /**
     * Checks if each character in the given array satisfies the rules defined in
     * the given array of CharacterRules.
     *
     * @param array           the array of characters to check
     * @param mustBeRules     the array of CharacterRules to check against
     * @param mustBeSatisfied the array to store the satisfaction of each rule
     */
    private static void checkMustBeRules(char[] array, CharacterRule[] mustBeRules, boolean[] mustBeSatisfied) {
        int i = 0;
        while (i < array.length) {
            char ch = array[i];
            int j = 0;
            while (j < mustBeRules.length) {
                if (mustBeRules[j].predicate.test(ch)) {
                    mustBeSatisfied[j] = true;
                }
                j++;
            }
            i++;
        }
    }

    /**
     * Checks the given character array against the given array of MustNotBeRules
     * and appends any errors to the
     * errorMessages StringBuilder.
     *
     * @param array          the character array to check against the MustNotBeRules
     * @param mustNotBeRules the array of MustNotBeRules to check against the
     *                       character array
     * @param addedErrors    the set of ErrorStrings that have already been added to
     *                       the errorMessages StringBuilder
     * @param errorMessages  the StringBuilder to append any errors to
     */
    private static void checkMustNotBeRules(char[] array, CharacterRule[] mustNotBeRules, Set<ErrorString> addedErrors,
            StringBuilder errorMessages) {
        int i = 0;

        while (i < array.length) {
            char ch = array[i];
            int j = 0;

            while (j < mustNotBeRules.length) {
                if (mustNotBeRules[j].predicate.test(ch) && addedErrors.add(mustNotBeRules[j].errorString)) {
                    appendErrorMessage(errorMessages, mustNotBeRules[j].errorString);
                }
                j++;
            }
            i++;
        }
    }

    /**
     * Accumulates errors based on the given rules.
     *
     * @param mustBeRules     the array of CharacterRules to check against
     * @param mustBeSatisfied the array to store the satisfaction of each rule
     * @param addedErrors     the set of ErrorStrings that have already been added
     *                        to the errorMessages StringBuilder
     * @param errorMessages   the StringBuilder to append any errors to
     */
    private static void accumulateErrors(CharacterRule[] mustBeRules, boolean[] mustBeSatisfied,
            Set<ErrorString> addedErrors, StringBuilder errorMessages) {
        int i = 0;

        while (i < mustBeRules.length) {
            if (!mustBeSatisfied[i] && mustBeRules[i].isSatisfied && addedErrors.add(mustBeRules[i].errorString)) {
                appendErrorMessage(errorMessages, mustBeRules[i].errorString);
            }
            i++;
        }
    }

    /**
     * Appends an error message to the given StringBuilder if it is not empty.
     *
     * @param errorMessages the StringBuilder to append the error message to
     * @param errorString   the ErrorString containing the error message to append
     */
    private static void appendErrorMessage(StringBuilder errorMessages, ErrorString errorString) {
        if (errorMessages.length() > 0) {
            errorMessages.append(", ");
        }
        errorMessages.append(errorString.getErrorMessage());
    }
}