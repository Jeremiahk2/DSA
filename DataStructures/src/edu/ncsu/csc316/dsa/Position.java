package edu.ncsu.csc316.dsa;

/**
 * A position represents a location within a data structure that stores a data
 * element. For example, a list node is a position that stores a list element. A
 * tree node is also a position that stores an element.
 * 
 * @author Dr. King
 *
 * @param <E> the type of data stored in the position
 */
public interface Position<E> {

    /**
     * Return the element stored at this position
     * 
     * @return the element stored at this position
     */
    E getElement();
}