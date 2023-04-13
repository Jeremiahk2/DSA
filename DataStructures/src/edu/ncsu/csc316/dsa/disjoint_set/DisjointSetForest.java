package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Disjoint Set abstract data type represents disjoint (an element is
 * contained in one and only one set) sets of elements. No duplicate elements
 * are allowed.
 * 
 * The Disjoint Set interface is based on a similar implementation of partition
 * structures developed for use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @param <E> the type of elements stored in the disjoint set
 */
public interface DisjointSetForest<E> {

    /**
     * Creates a new disjoint set to store the given element, and returns a
     * reference to the position that contains the given element
     * 
     * @param element the element that will be contained in the new disjoint set
     * @return a reference to the position that contains the given element and
     *         represents the new disjoint set
     */
    Position<E> makeSet(E element);

    /**
     * Returns a reference to the position that identifies the disjoint set that
     * contains the given element
     * 
     * @param element the element for which to locate
     * @return the position that identifies the disjoint set that contains the given
     *         element
     */
    Position<E> find(E element);

    /**
     * Unions two disjoint sets together into a single disjoint set
     * 
     * @param s the position that identifies the first disjoint set for the union
     * @param t the position that identifies the second disjoint set for the union
     */
    void union(Position<E> s, Position<E> t);

}