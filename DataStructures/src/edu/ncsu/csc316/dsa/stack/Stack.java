package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

/**
 * The Stack abstract data type defines behaviors that operate based on
 * last-in-first-out (LIFO).
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the stack
 */
public interface Stack<E> {

    /**
     * Adds a new element to the top of the stack
     * 
     * @param element the new element to add to the stack
     */
    void push(E element);

    /**
     * Removes and returns the top element from the stack
     * 
     * @return the top element from the stack
     * @throws EmptyStackException if the stack is empty
     */
    E pop();

    /**
     * Returns (but does not remove) the top element from the stack
     * 
     * @return the top element on the stack
     * @throws EmptyStackException if the stack is empty
     */
    E top();

    /**
     * Returns the number of elements stored in the stack
     * 
     * @return the number of elements stored in the stack
     */
    int size();

    /**
     * Returns true if the stack contains no elements, otherwise returns false
     * 
     * @return true if the stack contains no elements, otherwise returns false
     */
    boolean isEmpty();
}