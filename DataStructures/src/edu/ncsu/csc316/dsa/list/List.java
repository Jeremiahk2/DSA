package edu.ncsu.csc316.dsa.list;

/**
 * The List abstract data type represents an index-based list, where n elements
 * in the list are ordered from index 0 through index n.
 * 
 * All Lists must be {@link Iterable} to allow clients to iterate using for-each
 * loops.
 * 
 * The List interface is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 *
 * @author Dr. King
 * @param <E> the type of elements stored in the list
 */
public interface List<E> extends Iterable<E> {

    /**
     * Adds a new element to the list at the specified index.
     * 
     * @param index   the index at which to add the new element to the list
     * @param element the new element to add to the list
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */
    void add(int index, E element);

    /**
     * Adds a new element to the front of the list (index 0)
     * 
     * @param element the new element to add to the front of the list
     */
    void addFirst(E element);

    /**
     * Adds a new element to the end of the list (index n)
     * 
     * @param element the new element to add to the end of the list
     */
    void addLast(E element);

    /**
     * Returns the element at the front of the list (index 0)
     * 
     * @return the element at the front of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    E first();

    /**
     * Returns the element at the specified index of the list
     * 
     * @param index the index at which to retrieve the element
     * @return the element at the specified index of the list
     * @throws IndexOutOfBoundsException if the specified index is not a valid index
     *                                   based on the current state of the list
     */
    E get(int index);

    /**
     * Returns true if the list is empty; otherwise, returns false
     * 
     * @return true if the list is empty, or return false if the list is not empty
     */
    boolean isEmpty();

    /**
     * Returns the element at the end of the list (index n)
     * 
     * @return the element at the end of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    E last();

    /**
     * Removes and returns the element at the specified index of the list
     * 
     * @param index the index of the element to remove from the list
     * @return the element at the specified index of the list
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */
    E remove(int index);

    /**
     * Removes and returns the element at the front of the list (index 0)
     * 
     * @return the element at the front of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    E removeFirst();

    /**
     * Removes and returns the element at the end of the list (index n)
     * 
     * @return the element at the front of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    E removeLast();

    /**
     * Updates the element at the specified index of the list
     * 
     * @param index   the index at which an existing element should be updated
     * @param element the new element to store are the provided index
     * @return the original element that was replaced by the updated element
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */
    E set(int index, E element);

    /**
     * Returns the number of elements in the list
     * 
     * @return the number of elements in the list
     */
    int size();
}