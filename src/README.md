# P#3 - Sorted Doubly Link List ADT

<details>
    <summary>Instructions</summary>
    Create a sorted list ADT that implements the Unit 3 ListInterface.java, using a doubly linked list as the underlying data structure.

    No changes to *ListInterface.java* are to be made.

    A find helper method must be implemented and used in the same manner as the find method in the List ADTs developed in class. The application level must be able to switch the search logic between a sequential search and a binary search. A third option for the search logic may be added.

    In addition to the operations specified in *ListInterface.java*, an iterator class must be developed to allow the logical level to iterate through the data on the list using an enhanced for loop structure. Three types of iteration must be supported:

    1. In order, starting at the beginning of the list
    2. In reverse order, starting at the end of the list
    3. In random order, all items on the list are retrieved exactly one time
        * <span style="color: #2c0dd9;">Collections.shuffle()</span>      cannot be used
    The list ADT must include a public facing method that allows the logical level to indicate which type of iteration is to be done the next time an enhanced for loop executes.

    A <span style="color: 2c0dd9"> toString()</span> method must also be developed.

    Test cases and a test program must also be developed.

    * At least two test cases for each list operation and the toString, and at least one test case for each type of iteration.
        * Each test case must explicitly identify the expected output and expected change in the list.
    * Test program must be consistent with the set of the test cases.

</details>