package definition;


import adt.MyListAdt;

/*
 * This class is the Definition class for Link List Implementation

 */
public class MyList<E> implements MyListAdt<E> {
    /*
     * This variable will hold the size of the link list
     */
    public int size = 0;
    /*
     This reference variable will hold the value for the first node of our list
       Its Initial value is null when the list is empty
     */
    private Node<E> head;

    {
        head = null;
    }

    /*
     * This Method will return a node at the specified index.
     */
    private Node<E> getNode(int index) {
        /*
          A variable the will initially hold the starting value of the List
         */


        Node<E> response = head;
        /*
          A loop for traversing the whole list till the index
          and passing that node to response
         */

        for (int i = 0; i < index; i++) {
            /*
              one by one changing the value of response
             */
            response = response.getNext();

        }

        return response;

    }

    /*
      This Method add a Node in the list with data item at a specified index
     */
    public void add(E item, int index) {
        /*
         *index cannot be less then 0 and more than size of th link list
         * if such index passed throw an IndexOutOfBoundsException( Exception
         */
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        /*
          if index passed is 0 than call the addFirst() method
         */
        else if (index == 0) {
            addFirst(item);
        }
        /*
          call the addAfter() method and get the node value by calling getNode() method
         */
        else {
            addAfter(item, getNode(index - 1));
        }

    }

    /*
    this method will add a node at starting of linkedlist.
     */
    private void addFirst(E item) {
        /*
          create a new node with reference value that head initially was holding
          Change the reference of the head to the new node created
         */
        head = new Node<>(item, head);
        /*
          increase the size of the list by 1
         */
        size++;
    }

    /*
    This method will add a new node with a data item passed and after a specified node
     */
    private void addAfter(E item, Node<E> afterNode) {
        /*
          create a new node with reference value that afterNode initially was holding
          Change the reference of the afterNode to the new node created
         */
        afterNode.next = new Node<>(item, afterNode.next);
        size++;

    }

    /*
      This method will add a node to the last of the list.
     */
    @Override
    public void add(E item) {
        /*
          call the add() method and passed the size of the List as Index so that the node add to the last
         */
        add(item, size);

    }

    /*
     * This method will remove the first node from the list and return the data of that node
     */
    private void removeFirst() {
        /*
          make a temp variable to hold the initial head value
         */
        Node<E> temp = head;
        /*
          initialize the response variable to be returned
         */

        /*
          check if list is not empty i.e., head is not null
         */
        if (head != null) {
            /*
              change the reference of the head to the next node
             */
            head = head.getNext();
        }
        /*
          check if temp variable is not null
         */
        if (temp != null) {
            /*
              decrease the size of the list
             */
            size--;


        }

    }

    /*
      This method will remove a node after a specified node and will return data of that node
     */
    private void removeAfter(Node<E> afterNode) {
        /*
          make a temp variable to hold the reference of the afterNode (for the data to be returned)
         */
        Node<E> temp = afterNode.getNext();
        /*
          Check if temp is not null
         */
        if (temp != null) {
            /*
              change the reference of the node to the reference of the node to be deleted
             */
            afterNode.next = temp.getNext();
            /*
              decrease the size by 1
             */
            size--;
        }

    }

    /*
     * This method will remove a node at a specified index.
     */
    @Override
    public void remove(int index) {
        /*
          check if index is less then 0 or more than size of the list
          if yes throw an exception
         */
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        /*
          if passed index is 0 call the removeFirst() method
         */
        else if (index == 0) {
            removeFirst();
        }
        /*
          call the removeAfter() method and get the afterNode value by getNode() method
         */
        else {
            Node<E> previousNode = getNode(index - 1);
            removeAfter(previousNode);
        }

    }

    /*
      This method will return data of the node at a particular index
    */
    @Override
    public E getData(int index) {
        /*
          call the getNode() method and get the data of that node by getData() method
         */
        return getNode(index).getData();
    }

    /*
     * A private static class that will represent a single element of our list
     * this class has a data value  and a reference value
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        /*
         * Constructor to initialize the data and next fields of the node class
         *
         * @param data data of the node
         * @param next reference of the node
         */

        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        /*
         * Getter method of the data field
         *
         * @return data of the node
         */

        public E getData() {
            return data;
        }

        /*
         * Getter method of the next field
         *
         * @return reference of the node
         */
        public Node<E> getNext() {
            return next;
        }
    }
}