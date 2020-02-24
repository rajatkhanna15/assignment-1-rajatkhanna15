package adt;

public interface MyListAdt<E> {
    /*
    this is the adt for linked list data structure application that we will use in our contact application.
    this adt consist of certain methods.
    1. Add an item at the end or at an specified index.
    2. Remove an item at the end or at an specified index.
    3. Get the data present in the list at a particular index.
     */

    /*
    This method will add a node at the end of linked list.
    Also it will create a node with the data item passed by the user.


     */

    void add(E item);


    /*
    this method will remove a data item from a specified index.
     */
    void remove(int index);

    E getData(int index);


}
