
# ArraysUtils

## HWs

[![Java CI with Maven](https://github.com/Beersheva24VitaliyNovozhilov/ArraysUtils/actions/workflows/maven.yml/badge.svg)](https://github.com/Beersheva24VitaliyNovozhilov/ArraysUtils/actions/workflows/maven.yml)

* HW 5 - ArraysUtils basics
* [HW 6 - Sorting arrays](#hw-6---sorting-arrays) [added 11.07.2024]

Utility methods for working with arrays. Homework 5.

## Usage

To use the `ArraysUtils` class, you can simply import it into your project:

```java
import static io.p4r53c.telran.utils.ArraysUtils;
```

### Searching for a value in an array

To search for a value in an array, you can use the `searchByIndex` method:

```java
int[] array = {1, 2, 3, 4, 5};
int value = 3;
int index = ArraysUtils.searchByIndex(array, value);
```

This will return the index of the first occurrence of the value in the array, or -1 if the value is not found.

### Adding a value to an array

To add a value to an array, you can use the `addByCopyOf` method:

```java
int[] array = {1, 2, 3};
int value = 4;
int[] newArray = ArraysUtils.addByCopyOf(array, value);
```

This will return a new array with the value added at the end.

### Inserting a value into an array

To insert a value into an array at a specific index, you can use the `insertByArraycopy` method:

```java
int[] array = {1, 2, 3};
int index = 1;
int value = 4;
int[] newArray = ArraysUtils.insertByArraycopy(array, index, value);
```

This will return a new array with the value inserted at the specified index.

### Removing a value from an array

To remove a value from an array at a specific index, you can use the `removeByArraycopy` method:

```java
int[] array = {1, 2, 3};
int index = 1;
int[] newArray = ArraysUtils.removeByArraycopy(array, index);
```

This will return a new array with the value at the specified index removed.

## Sorting arrays

### HW 6 - Sorting arrays

### Sorting by pushing the maximum element to the end

To sort an array by pushing the maximum element to the end, you can use the `sortByPushing` method:

```java
ArraysUtils.sortByPushing(new int[] {3, 1, 2});
```

This will sort the array in ascending order by pushing the maximum element to the end in each iteration.

### Binary search

To search for a value in an array, you can use the `binarySearch` method:

```java
int index = ArraysUtils.binarySearch(new int[] {1, 2, 3}, 1);
```

This will return the index of the first occurrence of the value in the array.

### Insert in to sorted array

To insert a value into a sorted array, you can use the `insertSorted` method:

```java
int[] newArray = ArraysUtils.insertSorted(new int[] {1, 2, 3}, 0);
```

This will return the new sorted array with the value inserted in the right position.

### Determining if only one swap is needed to sort the array

To determine if only one swap is needed to sort the array, you can use the `isOneSwapNeeded` method:

```java
boolean result = ArraysUtils.isOneSwapNeeded(new int[] {4, 2, 3, 1});
```

This will return true if only one swap is needed to sort the array, false otherwise.

## Contributing

It is a study project and does not require any contributions.

## License

It is study project and does not require any license.
