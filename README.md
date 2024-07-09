
# ArraysUtils

## HW 5

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

## Contributing

It is a study project and does not require any contributions.

## License

It is study project and does not require any license.
