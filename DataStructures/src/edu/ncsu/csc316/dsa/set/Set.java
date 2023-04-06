package edu.ncsu.csc316.dsa.set;

/**
 * The Set abstract data type represents an unordered collection of elements.
 * Duplicate elements are not allowed in Sets.
 * 
 * All Sets must be {@link Iterable} to allow clients to iterate using for-each
 * loops.
 * 
 * The Set interface is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 *
 * @author Dr. King
 * @param <E> the type of elements stored in the set
 */
public interface Set<E> extends Iterable<E> {

    /**
     * Adds a new element to the set
     * 
     * @param element the element to add to the set
     */
    void add(E element);

    /**
     * Returns true if the set contains the given element
     * 
     * @param element the element for which to check if it exists within the set
     * @return true if the set contains the given element; otherwise, return false
     */
    boolean contains(E element);

    /**
     * Removes and returns the given element from the set
     * 
     * @param element the element to remove from the set
     * @return the element removed from the set
     */
    E remove(E element);

    /**
     * Returns true to indicate the set contains no elements, or false to indicate
     * the set contains at least 1 element.
     * 
     * @return true if the set contains no elements; otherwise, return false
     */
    boolean isEmpty();

    /**
     * Returns the number of elements stored within the set
     * 
     * @return the number of elements stored within the set
     */
    int size();

    /**
     * Adds all elements from the provided set into the current set. Mathematically,
     * "union".
     * 
     * @param other the reference set from which to add all elements into the
     *              current set
     */
    void addAll(Set<E> other);

    /**
     * Removes all entries from the current set that are not also contained in the
     * provided set. Mathematically, "intersection".
     * 
     * @param other the reference set from which to remove all entries from the
     *              current set that are not also contained in reference set
     */
    void retainAll(Set<E> other);

    /**
     * Removes all entries from the current set that are contained in the provided
     * set. Mathematically, "substraction".
     * 
     * @param other the reference set from which to remove all entries from the
     *              current set that are also contained in the reference set
     */
    void removeAll(Set<E> other);
}