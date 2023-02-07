package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The Queue abstract data type defines behaviors that operate based on
 * first-in-first-out (FIFO).
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the queue
 */
public interface Queue<E> {

	/**
	 * Adds a new element to the back of the queue
	 * 
	 * @param element the new element to add to the queue
	 */
	void enqueue(E element);

	/**
	 * Removes and returns the front/first element in the queue
	 * 
	 * @return the front/first element in the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	E dequeue();

	/**
	 * Returns (but does not remove) the front/first element in the queue
	 * 
	 * @return the front/first element in the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	E front();

	/**
	 * Returns the number of elements stored in the queue
	 * 
	 * @return the number of elements stored in the queue
	 */
	int size();

	/**
	 * Returns true if the queue contains no elements, otherwise returns false
	 * 
	 * @return true if the queue does not contain any elements, otherwise returns
	 *         false
	 */
	boolean isEmpty();
}